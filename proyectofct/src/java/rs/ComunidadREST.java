/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.entities.Comunidad;
import model.entities.DTOs.ComunidadDTO;
import model.entities.DTOs.ExpulsadoDTO;
import model.entities.DTOs.UnionDTO;
import model.entities.DTOs.UsuarioDTO;
import model.entities.Expulsados;
import model.entities.Usuario;
import model.services.ComunidadService;
import model.services.ExpulsadosService;
import model.services.UsuarioService;

/**
 *
 * @author mangel
 */
@Path("/comunidad")
public class ComunidadREST {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    ComunidadService cs = new ComunidadService(emf);
    UsuarioService us = new UsuarioService(emf);
    ExpulsadosService es = new ExpulsadosService(emf);
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
    @POST
    @Path("/unir")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response joinComunidad (UnionDTO unionDTO) throws Exception {
        Usuario usu = us.findUsuario(unionDTO.usuarioId);
        Comunidad com = cs.findComunidad(unionDTO.comunidadId);
        if (usu == null || com == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuario o Comunidad no encontrados").build();
        }
        com.getUsuarios().add(usu);
        int numMiembros = com.getNumMiembros();
        numMiembros++;
        com.setNumMiembros(numMiembros);
        usu.getComunidades().add(com);
        cs.edit(com); 
        us.edit(usu);

            return Response.status(Response.Status.CREATED).build();

    }
    @GET
    @Path("/{comunidadId}/pertenece/{usuarioId}")
    public Response pertenece(@PathParam("comunidadId") Long comunidadId, @PathParam("usuarioId") Long usuarioId) {
        Comunidad com = cs.findComunidad(comunidadId);
        boolean pertenece = com.getUsuarios().stream()
        .anyMatch(u -> u.getId().equals(usuarioId));
        return Response.ok(Collections.singletonMap("pertenece", pertenece)).build();
    }
    @DELETE
    @Path("/{comunidadId}/salir/{usuarioId}")
    public Response salirComunidad (@PathParam("usuarioId") Long usuarioId, @PathParam("comunidadId") Long comunidadId) throws Exception {
        Comunidad com = cs.findComunidad(comunidadId);
        Usuario usu = us.findUsuario(usuarioId);
        if (com == null || usu == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuario o Comunidad no encontrados").build();
        }
        com.getUsuarios().removeIf(u -> u.getId().equals(usuarioId));
        usu.getComunidades().removeIf(c -> c.getId().equals(comunidadId));
        int numMiembros = com.getNumMiembros();
        numMiembros--;
        com.setNumMiembros(numMiembros);
        cs.edit(com);
        us.edit (usu);
        return Response.status(Response.Status.ACCEPTED).build();
    }
    @POST
    public Response crearComunidad(ComunidadDTO comunidadDto) {
        Comunidad comunidad = new Comunidad();
        comunidad.setNombre(comunidadDto.getNombre());
        comunidad.setDescripcion(comunidadDto.getDescripcion());
        comunidad.setReglas(comunidadDto.getReglas());
        comunidad.setFechaCreacion(new Date());
        Long idUsuAdmin = comunidadDto.getIdUsuAdmin();
        Usuario usuAdmin = us.findUsuario(idUsuAdmin);
        comunidad.setUsuAdmin(usuAdmin);
        cs.create(comunidad);
        return Response.status(Response.Status.CREATED).build();
    }
    @GET
    @Path("/ids")
    public Response getIds () {
        List<Comunidad> comunidades = cs.findComunidadEntities();
        
        List<Long> ids = comunidades.stream()
                                .map(Comunidad::getId)
                                .collect(Collectors.toList());

        return Response.ok(ids).build();
    }
    @GET
    @Path("/{comunidadId}/usuarios")
    public Response getUsuariosDeComunidad(@PathParam("comunidadId") Long comunidadId) {
        Comunidad comunidad = cs.findComunidad(comunidadId);
        if (comunidad == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Comunidad no encontrada con id: " + comunidadId).build();
        }

        List<UsuarioDTO> usuarioDTOs = comunidad.getUsuarios().stream()
            .map(usuario -> new UsuarioDTO(usuario))
            .collect(Collectors.toList());

        return Response.ok(usuarioDTOs).build();
    }
    
    @POST
    @Path("/{comunidadId}/expulsar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response expulsarUsuario(
        @PathParam("comunidadId") Long comunidadId,
        ExpulsadoDTO expulsionDTO) {

        try {
            // 1. Buscar entidades
            Comunidad comunidad = cs.findComunidad(comunidadId);
            Usuario usuario = us.findUsuario(expulsionDTO.getUsuarioId());

            if (comunidad == null || usuario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Comunidad o usuario no encontrados").build();
            }


            // 3. Verificar membresía (versión mejorada)
            boolean usuarioEnComunidad = comunidad.getUsuarios().stream()
                .anyMatch(u -> u.getId().equals(usuario.getId()));

            if (!usuarioEnComunidad) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("El usuario no pertenece a esta comunidad").build();
            }

            // 4. Realizar la expulsión
            Expulsados expulsion = new Expulsados();
            expulsion.setUsuario(usuario);
            expulsion.setComunidad(comunidad);
            expulsion.setRazon(expulsionDTO.getRazon());
            expulsion.setFechaFin(expulsionDTO.getFechaFin());

            // 5. Actualizar relaciones (versión segura)
            comunidad.getUsuarios().removeIf(u -> u.getId().equals(usuario.getId()));
            usuario.getComunidades().removeIf(c -> c.getId().equals(comunidad.getId()));

            comunidad.setNumMiembros(comunidad.getNumMiembros() - 1);

            // 6. Guardar cambios
            cs.edit(comunidad);
            us.edit(usuario);
            es.create(expulsion);

            return Response.ok().entity("Usuario expulsado correctamente").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al expulsar usuario: " + e.getMessage()).build();
        }
    }
}
