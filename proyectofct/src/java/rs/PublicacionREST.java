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
import model.entities.Comunidad;
import model.entities.DTOs.ComunidadDTO;
import model.entities.DTOs.PublicacionDTO;
import model.entities.Publicacion;
import model.entities.Usuario;
import model.services.ComentarioService;
import model.services.ComunidadService;
import model.services.PublicacionService;
import model.services.UsuarioService;

/**
 *
 * @author mangel
 */
@Path("/publicacion")
public class PublicacionREST {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    PublicacionService ps = new PublicacionService(emf);
    UsuarioService us = new UsuarioService(emf);
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
    public Response getPublicacion(@PathParam("id") Long id) {
        Publicacion publicacion = ps.findPublicacion(id);
        PublicacionDTO publicacionDTOs = new PublicacionDTO(publicacion);
        return Response.ok(publicacionDTOs).build();
    }
    @DELETE
    @Path("/{id}")
    public Response deletePublicacion(@PathParam("id") Long id) {
             try {
        ComentarioService cs = new ComentarioService(emf);
        PublicacionService ps = new PublicacionService(emf);
        Publicacion publicacion = ps.findPublicacion(id);
        if (publicacion == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Publicación no encontrada con id: " + id).build();
        }

        Usuario autor = publicacion.getUsuario();
               if (autor != null) {
                   int num = autor.getNumPublicaciones();
                   autor.setNumPublicaciones(Math.max(0, num - 1)); // Evita valores negativos
                   us.edit(autor); // Persistir cambios en el autor
               }
        boolean seElimino;
        do {
            seElimino = false;
            List<Comentario> comentarios = cs.findComentarioEntitiesByPublicacion(id);
            for (Comentario comentario : comentarios) {
                List<Comentario> respuestas = cs.findRespuestasByComentarioPadre(comentario.getId());
                if (respuestas == null || respuestas.isEmpty()) {
                    cs.destroy(comentario.getId());
                    seElimino = true;
                }
            }
        } while (seElimino);

        ps.destroy(id);
        return Response.ok().build();

    } catch (Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error al eliminar la publicación con id: " + id + " - " + e.getMessage())
                .build();
    }
    }
    @POST
    public Response createPubli ( PublicacionDTO publiDTO){
            Publicacion newPubli = new Publicacion();
            newPubli.setTitulo(publiDTO.getTitulo());
            newPubli.setContenido(publiDTO.getContenido());
            newPubli.setFechaPublicacion(new Date());
            
            Usuario usuario = new UsuarioService(emf).findUsuario(publiDTO.getUsuarioId());
            Comunidad comunidad = new ComunidadService(emf).findComunidad(publiDTO.getComunidadId());
            newPubli.setUsuario(usuario);
            newPubli.setComunidad(comunidad);
            int numPublicaciones = usuario.getNumPublicaciones();
            numPublicaciones++;
            usuario.setNumPublicaciones(numPublicaciones);
            System.out.println(usuario.getNumComentarios());
            try {
                us.edit(usuario);
            } catch (Exception ex) {
                Logger.getLogger(PublicacionREST.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setNumPublicaciones(numPublicaciones);
            
            ps.create(newPubli);
            return Response.status(Response.Status.CREATED).build();
    }
    @POST
    @Path("/{publicacionId}/upvote")
    public Response valorarPublicacion(@PathParam("publicacionId") Long publiId){
        Publicacion publi = ps.findPublicacion(publiId);
        int votos = publi.getNumVotos();
        votos++;
        publi.setNumVotos(votos);
        try {
            ps.edit(publi);
            return Response.status(Response.Status.OK).build(); 
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
        @POST
    @Path("/{publicacionId}/downvote")
    public Response valorarPublicacionNegativo(@PathParam("publicacionId") Long publiId){
        Publicacion publi = ps.findPublicacion(publiId);
        int votos = publi.getNumVotos();
        votos--;
        publi.setNumVotos(votos);
        try {
            ps.edit(publi);
            return Response.status(Response.Status.OK).build(); 
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @GET
    @Path("/usuario/{id}/posts")
    public Response getPublicacionesByUsuario(@PathParam("id") Long id) {
        // Buscar al usuario
        Usuario usuario = us.findUsuario(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con id: " + id).build();
        }

        // Obtener publicaciones por usuario
        List<Publicacion> publicaciones = ps.findPublicacionesByUsuarioId(id);
        List<PublicacionDTO> publicacionDTOs = publicaciones.stream()
                .map(PublicacionDTO::new)
                .collect(Collectors.toList());

        return Response.ok(publicacionDTOs).build();
    }
}

