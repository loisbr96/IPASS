package nl.hu.ipass.webservices;

import nl.hu.ipass.domain.Huis;
import nl.hu.ipass.domain.Slaapplek;
import nl.hu.ipass.domain.Student;
import nl.hu.ipass.services.ServiceProvider;
import nl.hu.ipass.services.SlaapplekService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/slaapplek")
@RolesAllowed("student")
@Produces("application/json")
public class SlaapplekResource {
    SlaapplekService service = ServiceProvider.getSlaapplekService();

    @POST
    public Response newSlaapplek(@FormParam("datum") String datum,
                                 @FormParam("huis") int huisId,
                                 @Context ContainerRequestContext context) {
        MySecurityContext msc = (MySecurityContext) context.getSecurityContext();

        Student student = ServiceProvider.getStudentService().findById(msc.getId());

        Huis huis = ServiceProvider.getHuisService().findById(huisId);
        if(huis == null){
            return Response.status(404).build();
        } else{
            Slaapplek newSlaapplek = service.save(new Slaapplek(datum, huis, student));
            return Response.ok(newSlaapplek).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateSlaapplek(@PathParam("id") int id,
                                    @FormParam("datum") String datum,
                                    @FormParam("huis") int huisId,
                                    @Context ContainerRequestContext context){
        MySecurityContext msc = (MySecurityContext) context.getSecurityContext();

        Student student = ServiceProvider.getStudentService().findById(msc.getId());

        Huis huis = ServiceProvider.getHuisService().findById(huisId);
        if(huis == null){
            return Response.status(404).build();
        } else{
            Slaapplek newSlaapplek = service.save(new Slaapplek(datum, huis, student));
            return Response.ok(newSlaapplek).build();
        }
    }

    @GET
    @Path("/huis/{id}/{datum}")
    public Slaapplek getHuisAndDatum(@PathParam("id") int huisId,
                                     @PathParam("datum") String datum){
        Huis huis = ServiceProvider.getHuisService().findById(huisId);

        return service.findByHuisAndDatum(huis,datum).get(0);
    }

    @GET
    @Path("/datum/{datum}")
    public Slaapplek getHuisForDatum(@PathParam("datum") String datum,
                                       @Context ContainerRequestContext context) {
        MySecurityContext msc = (MySecurityContext) context.getSecurityContext();

        Student student = ServiceProvider.getStudentService().findById(msc.getId());

        return service.findByStudentAndDatum(student, datum);
    }
}
