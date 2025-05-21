/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import model.entities.Comunidad;
import model.entities.DTOs.ReportesDTO;
import model.entities.Publicacion;
import model.entities.Reportes;
import model.entities.Usuario;
import model.services.ComunidadService;
import model.services.PublicacionService;
import model.services.ReportesService;
import model.services.UsuarioService;
import model.services.exceptions.NonexistentEntityException;

/**
 *
 * @author mangel
 */
@Path("/reportes")
public class ReportarREST {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    ReportesService rs = new ReportesService(emf);
    UsuarioService us = new UsuarioService(emf);
    PublicacionService ps = new PublicacionService(emf);
    ComunidadService cs = new ComunidadService(emf);
    @GET
    @Path("/comunidad/{id}")
    public Response getReportesPorComunidad(@PathParam("id") Long comunidadId) {
        Comunidad comunidad = cs.findComunidad(comunidadId);
        if (comunidad == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Comunidad no encontrada").build();
        }

        List<Reportes> reportes = rs.findReportesPorComunidad(comunidad);
        List<ReportesDTO> reportesDTO = reportes.stream()
            .map(ReportesDTO::new)
            .collect(Collectors.toList());
        return Response.ok(reportesDTO).build();
    }
    @POST
    public Response createReporte(ReportesDTO reporteDTO) {
          // Validaciones básicas
        if (reporteDTO.getMotivo() == null || reporteDTO.getMotivo().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }

        if (reporteDTO.getUsuarioId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }

        // Crear la entidad Reportes a partir del DTO
        Reportes nuevoReporte = new Reportes();
        nuevoReporte.setMotivo(reporteDTO.getMotivo());
        nuevoReporte.setFechaReporte(new Date()); // Fecha actual
        
        // Buscar y asignar relaciones
        Usuario usuario = us.findUsuario(reporteDTO.getUsuarioId());
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        nuevoReporte.setUsuario(usuario);

        if (reporteDTO.getPublicacionId() != null) {
            Publicacion publicacion = ps.findPublicacion(reporteDTO.getPublicacionId());
            nuevoReporte.setPublicacion(publicacion);
        }

        if (reporteDTO.getComunidadId() != null) {
            Comunidad comunidad = cs.findComunidad(reporteDTO.getComunidadId());
            nuevoReporte.setComunidad(comunidad);
        }

        // Persistir el reporte
        rs.create(nuevoReporte);

        // Devolver el reporte creado como DTO
        return Response.status(Response.Status.CREATED)
                .build();

    } 

    @DELETE
    @Path("/{id}")
    public Response deleteReport (@PathParam("id") Long id ) {
        Reportes reporteDel = rs.findReportes(id);
        if (reporteDel == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        try {
            rs.destroy(id);
             return Response.noContent().build();
        } catch (NonexistentEntityException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    
}
