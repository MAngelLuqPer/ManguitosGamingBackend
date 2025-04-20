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
import model.entities.Comentario;
import model.entities.DTOs.ComentarioDTO;
import model.services.ComentarioService;

/**
 *
 * @author mangel
 */
@Path("/comentarios")
public class ComentarioREST {
    @GET
    public Response getAllComentarios() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    ComentarioService cs = new ComentarioService(emf);
    List<Comentario> comentarios = cs.findComentarioEntities();
    List<ComentarioDTO> comentarioDTOs = comentarios.stream()
        .map(ComentarioDTO::new)
        .collect(Collectors.toList());

    return Response.ok(comentarioDTOs).build();
    }

}

