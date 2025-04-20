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
    @GET
    public Response getAllComunidades() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectofctPU");
    ComunidadService cs = new ComunidadService(emf);
    List<Comunidad> comunidades = cs.findComunidadEntities();
    List<ComunidadDTO> comunidadDTOs = comunidades.stream()
        .map(ComunidadDTO::new)
        .collect(Collectors.toList());

    return Response.ok(comunidadDTOs).build();
    }

}

