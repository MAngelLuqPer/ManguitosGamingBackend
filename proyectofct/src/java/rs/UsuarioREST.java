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
import model.entities.DTOs.UsuarioDTO;
import model.entities.Usuario;
import model.services.UsuarioService;

/**
 *
 * @author mangel
 */
@Path("/usuario")
public class UsuarioREST {
    @GET
    public Response getAllUsuarios() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    UsuarioService us = new UsuarioService(emf);
    List<Usuario> usuarios = us.findUsuarioEntities();
    List<UsuarioDTO> usuarioDTOs = usuarios.stream()
        .map(UsuarioDTO::new)
        .collect(Collectors.toList());
    return Response.ok(usuarioDTOs).build();
    }

}

