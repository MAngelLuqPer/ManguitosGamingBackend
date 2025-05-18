package rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
public class TestREST {
    @GET
    public Response hello() {
        return Response.ok("Hola desde API").build();
    }
}
