package nl.hu.ipass.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class TestResource {

    @GET
    public Response test(){
        return Response.ok("hallo, dit is een test").build();
    }


}
