/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import model.entities.Comunidad;
import model.entities.DTOs.ComunidadDTO;
import model.services.ComunidadService;

/**
 *
 * @author mangel
 */
@Path("/comunidad")
public class ComunidadREST {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    ComunidadService cs = new ComunidadService(emf);
    @GET
    public Response getAllComunidades() {
    List<Comunidad> comunidades = cs.findComunidadEntities();
    List<ComunidadDTO> comunidadDTOs = comunidades.stream()
        .map(ComunidadDTO::new)
        .collect(Collectors.toList());

    return Response.ok(comunidadDTOs).build();
    }
    @GET
    @Path("/{id}")
    public Response getComunidadById(@javax.ws.rs.PathParam("id") Long id) {
        Comunidad comunidad = cs.findComunidad(id);
        if (comunidad == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Comunidad no encontrada con id: " + id).build();
        }

        ComunidadDTO comunidadDTO = new ComunidadDTO(comunidad);
        return Response.ok(comunidadDTO).build();

    }
    
}

