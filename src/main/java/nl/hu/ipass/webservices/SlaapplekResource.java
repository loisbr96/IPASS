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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Hier wordt de class bepaald: */
@Path("/slaapplek")
@RolesAllowed("student")
@Produces("application/json")
public class SlaapplekResource {
    SlaapplekService service = ServiceProvider.getSlaapplekService();

    /*De response om een nieuwe slaapplek aan te maken d.m.v. van de save functie
    * ook wordt de slaapplek hier geupdate*/
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
            Slaapplek slaapplek = service.findByStudentAndDatum(student,datum );
            if(slaapplek == null){
                Slaapplek newSlaapplek = service.save(new Slaapplek(datum, huis, student));
                return Response.ok(newSlaapplek).build();
            }else{
                slaapplek.setHuis(huis);
                service.update(slaapplek);
                return Response.ok(slaapplek).build();
            }
        }
    }

    /*De Response om een slaapplek te wijzigen d.m.v. de opgegeven datum.
    * Kan alleen gewijzigt worden van de ingelogde gebruiker*/
    @PUT
    @Path("/{datum}")
    public Response updateSlaapplek(@PathParam("datum") String datum,
                                    @FormParam("huis") int huisId,
                                    @Context ContainerRequestContext context){
        MySecurityContext msc = (MySecurityContext) context.getSecurityContext();

        Student student = ServiceProvider.getStudentService().findById(msc.getId());

        Huis huis = ServiceProvider.getHuisService().findById(huisId);
        if(huis == null){
            Map<String, String> geenHuis = new HashMap<String, String>();
            geenHuis.put("error","Dit huis bestaat niet" );
            return Response.status(404).entity(geenHuis).build();
        } else{
            Slaapplek slaapplek = service.findByStudentAndDatum(student, datum);
            slaapplek.setHuis(huis);
            service.update(slaapplek);
            Map<String, String> updateSlaapplek = new HashMap<String, String>();
            updateSlaapplek.put("ok","Slaapplek geupdate" );
            return Response.status(200).entity(updateSlaapplek).build();
        }
    }

    /*De response om een slaapplek te krijgen op basis van het id en datum.
    * Alle studenten die in dat huis slapen op die datum worden als lijst terug gegeven*/
    @GET
    @Path("/huis/{id}/{datum}")
    public List<Slaapplek> getHuisAndDatum(@PathParam("id") int huisId,
                                           @PathParam("datum") String datum){
        Huis huis = ServiceProvider.getHuisService().findById(huisId);

        return service.findByHuisAndDatum(huis,datum);
    }

    /*Response om van die datum voor de ingelogde student de slaapplek terug te krijgen.*/
    @GET
    @Path("/datum/{datum}")
    public Slaapplek getHuisForDatum(@PathParam("datum") String datum,
                                       @Context ContainerRequestContext context) {
        MySecurityContext msc = (MySecurityContext) context.getSecurityContext();

        Student student = ServiceProvider.getStudentService().findById(msc.getId());

        return service.findByStudentAndDatum(student, datum);
    }
}
