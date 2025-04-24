/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.entities.DTOs.UsuarioDTO;
import model.entities.DTOs.UsuarioLoginDTO;
import model.entities.Usuario;
import model.services.UsuarioService;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author mangel
 */
@Path("/usuario")
public class UsuarioREST {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    UsuarioService us = new UsuarioService(emf);
    @GET
    public Response getAllUsuarios() {
    List<Usuario> usuarios = us.findUsuarioEntities();
    List<UsuarioDTO> usuarioDTOs = usuarios.stream()
        .map(UsuarioDTO::new)
        .collect(Collectors.toList());
    return Response.ok(usuarioDTOs).build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUsuario(UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setEmail(usuarioDTO.getEmail());
            if (us.findUsuarioByEmail(usuario.getEmail()) != null )  {
                return Response.status(Response.Status.CONFLICT).build();
            }
            usuario.setDescripcion(usuarioDTO.getDescripcion());
            usuario.setPrivacidad(usuarioDTO.isPrivacidad());
            usuario.setFechaCreacion(new Date());
            String hashedPassword = BCrypt.hashpw(usuarioDTO.getPwd(), BCrypt.gensalt());
            usuario.setPwd(hashedPassword);

            us.create(usuario);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el usuario: " + e.getMessage()).build();
        }
    }

    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UsuarioLoginDTO login) {
        
        Usuario usuario = us.findUsuarioByEmail(login.getEmail());

        if (usuario == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Email no encontrado").build();
        }

        if (BCrypt.checkpw(login.getPwd(), usuario.getPwd())) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            return Response.ok(usuarioDTO).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Contraseña incorrecta").build();
        }
    }
}


