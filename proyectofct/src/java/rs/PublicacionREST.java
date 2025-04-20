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
    @GET
    public Response getAllPublicaciones() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    PublicacionService ps = new PublicacionService(emf);
    List<Publicacion> publicaciones = ps.findPublicacionEntities();
    List<PublicacionDTO> publicacionDTOs = publicaciones.stream()
        .map(PublicacionDTO::new)
        .collect(Collectors.toList());

    return Response.ok(publicacionDTOs).build();
    }

}

