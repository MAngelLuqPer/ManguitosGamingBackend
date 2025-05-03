/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import model.entities.DTOs.ComunidadDTO;
import model.entities.DTOs.PublicacionDTO;
import model.entities.Publicacion;
import model.services.PublicacionService;

/**
 *
 * @author mangel
 */
@Path("/publicacion")
public class PublicacionREST {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    PublicacionService ps = new PublicacionService(emf);
    @GET
    public Response getAllPublicaciones() {
    List<Publicacion> publicaciones = ps.findPublicacionEntities();
    List<PublicacionDTO> publicacionDTOs = publicaciones.stream()
        .map(PublicacionDTO::new)
        .collect(Collectors.toList());

    return Response.ok(publicacionDTOs).build();
    }
    @GET
    @Path("/comunidad/{id}/posts")
    public Response getPublicacionesByComunidad(@javax.ws.rs.PathParam("id") Long id) {
        List<Publicacion> publicaciones = ps.findPublicacionesByComunidadId(id);
        List<PublicacionDTO> publicacionDTOs = publicaciones.stream()
                .map(PublicacionDTO::new)
                .collect(Collectors.toList());

        return Response.ok(publicacionDTOs).build();
    }
    @GET
    @Path("/{id}")
    public Response getPublicacion(@javax.ws.rs.PathParam("id") Long id) {
        Publicacion publicacion = ps.findPublicacion(id);
        PublicacionDTO publicacionDTOs = new PublicacionDTO(publicacion);
        return Response.ok(publicacionDTOs).build();
    }
    @DELETE
    @Path("/{id}")
        public Response deletePublicacion(@javax.ws.rs.PathParam("id") Long id) {
        System.out.println(id);
        try {
            ps.destroy(id);
            return Response.noContent().build(); // 204 No Content si se elimina correctamente
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Publicación no encontrada con id: " + id).build();
        }
    }
    
}

