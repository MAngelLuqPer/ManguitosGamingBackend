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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import model.entities.Comentario;
import model.entities.DTOs.ComentarioDTO;
import model.entities.Publicacion;
import model.entities.Usuario;
import model.services.ComentarioService;
import model.services.PublicacionService;
import model.services.UsuarioService;
import model.services.exceptions.NonexistentEntityException;

/**
 *
 * @author mangel
 */
@Path("/comentarios")
public class ComentarioREST {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    ComentarioService cs = new ComentarioService(emf);
    UsuarioService us = new UsuarioService(emf);
    PublicacionService ps = new PublicacionService(emf);
    @GET
    public Response getAllComentarios() {
    List<Comentario> comentarios = cs.findComentarioEntities();
    List<ComentarioDTO> comentarioDTOs = comentarios.stream()
        .map(ComentarioDTO::new)
        .collect(Collectors.toList());

    return Response.ok(comentarioDTOs).build();
    }
    @GET
    @Path("/publicacion/{publicacionId}")
    public Response getComentariosByPublicacion(@PathParam("publicacionId") Long publicacionId) {

        List<Comentario> comentarios = cs.findComentarioEntitiesByPublicacion(publicacionId);
        List<ComentarioDTO> comentarioDTOs = comentarios.stream()
            .map(ComentarioDTO::new)
            .collect(Collectors.toList());

        return Response.ok(comentarioDTOs).build();
    }
    @POST
    public Response crearComentario(ComentarioDTO comentarioDTO) {
       
        try {
            Comentario comentario = new Comentario();
            comentario.setContenido(comentarioDTO.getContenido());
            comentario.setFechaComentario(new Date());

            // Obtener usuario
            Usuario usuario = us.findUsuario(comentarioDTO.getUsuarioId());
            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuario no encontrado").build();
            }
            comentario.setUsuario(usuario);
            int numComentariosUsuario = usuario.getNumComentarios();
            numComentariosUsuario++;
            usuario.setNumComentarios(numComentariosUsuario);
            us.edit(usuario);
            // Obtener publicación
            Publicacion publicacion = ps.findPublicacion(comentarioDTO.getPublicacionId());
            if (publicacion == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Publicación no encontrada").build();
            }
            comentario.setPublicacion(publicacion);

            cs.create(comentario);

            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear comentario: " + e.getMessage()).build();
        }
    }
    @DELETE
    @Path("/{id}")
        public Response deleteComment(@PathParam("id") Long comId) {
              try {
            Comentario comentario = cs.findComentario(comId); // Necesario para obtener el autor
            if (comentario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Comentario no encontrado con id: " + comId).build();
            }

            // 1. Eliminar recursivamente las respuestas
            List<Comentario> respuestas = cs.findRespuestasByComentarioPadre(comId);
            for (Comentario respuesta : respuestas) {
                deleteComment(respuesta.getId()); // Llamada recursiva
            }

            // 2. Decrementar numComentarios del autor
            Usuario autor = comentario.getUsuario(); // Asegúrate de tener getAutor() en la entidad Comentario
            if (autor != null) {
                int num = autor.getNumComentarios();
                autor.setNumComentarios(Math.max(0, num - 1)); // Evita negativos
                us.edit(autor); // Guarda cambios
            }

            // 3. Eliminar el comentario raíz
            cs.destroy(comId);
            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (NonexistentEntityException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Comentario no encontrado con id: " + comId).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al eliminar comentario: " + e.getMessage()).build();
        }
    }
    @POST
    @Path("/responder")
    public Response responderComentario(ComentarioDTO comentarioDTO) {
        try {
            Comentario comentario = new Comentario();
            comentario.setContenido(comentarioDTO.getContenido());
            comentario.setFechaComentario(new Date());

            // Obtener usuario
            Usuario usuario = us.findUsuario(comentarioDTO.getUsuarioId());
            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuario no encontrado").build();
            }
            comentario.setUsuario(usuario);
            // Obtener publicación
            Publicacion publicacion = ps.findPublicacion(comentarioDTO.getPublicacionId());
            if (publicacion == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Publicación no encontrada").build();
            }
            comentario.setPublicacion(publicacion);

            // Obtener comentario padre
            if (comentarioDTO.getComentarioPadreId() != null) {
                Comentario comentarioPadre = cs.findComentario(comentarioDTO.getComentarioPadreId());
                if (comentarioPadre == null) {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Comentario padre no encontrado").build();
                }
                comentario.setComentarioPadre(comentarioPadre);
            }

            cs.create(comentario);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al responder comentario: " + e.getMessage()).build();
        }
    }
    @GET
    @Path("/respuestas/{comentarioId}")
    public Response getRespuestasByComentario(@PathParam("comentarioId") Long comentarioId) {
        try {
            List<Comentario> respuestas = cs.findRespuestasByComentarioPadre(comentarioId);
            List<ComentarioDTO> respuestaDTOs = respuestas.stream()
                    .map(ComentarioDTO::new)
                    .collect(Collectors.toList());

            return Response.ok(respuestaDTOs).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener respuestas: " + e.getMessage()).build();
        }
    }
    @GET
    @Path("/comunidad/{comunidadId}")
    public Response getComentariosByComunidad(@PathParam("comunidadId") Long comunidadId) {
        try {
            // Obtener todas las publicaciones de la comunidad
            List<Publicacion> publicaciones = ps.findPublicacionesByComunidadId(comunidadId);
            if (publicaciones == null || publicaciones.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No se encontraron publicaciones para la comunidad con id: " + comunidadId).build();
            }

            // Obtener comentarios de todas las publicaciones
            List<Comentario> todosComentarios = publicaciones.stream()
                    .flatMap(p -> cs.findComentarioEntitiesByPublicacion(p.getId()).stream())
                    .collect(Collectors.toList());

            List<ComentarioDTO> comentarioDTOs = todosComentarios.stream()
                    .map(ComentarioDTO::new)
                    .collect(Collectors.toList());

            return Response.ok(comentarioDTOs).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener comentarios de la comunidad: " + e.getMessage()).build();
        }
    }
}

