package nl.hu.ipass.webservices;

import nl.hu.ipass.domain.Huis;
import nl.hu.ipass.services.HuisService;
import nl.hu.ipass.services.ServiceProvider;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Hier wordt de methode gedefinieerd*/
@Path("/huis")
@RolesAllowed("student")
@Produces("application/json")
public class HuisResource {
    HuisService service = ServiceProvider.getHuisService();

    /*Response om een nieuw huis aan te maken d.m.v. opgegeven waarden via de functie save()*/
    @POST
    public Response newHuis(@FormParam("naam") String naam,
                            @FormParam("straatnaam") String straatnaam,
                            @FormParam("huisnummer") int huisnummer,
                            @FormParam("postcode") String postcode,
                            @FormParam("plaatsnaam") String plaatsnaam){
        Huis newHuis = service.save(new Huis(naam,straatnaam ,huisnummer ,postcode ,plaatsnaam));
        if(newHuis == null){
            Map<String, String> geenHuis = new HashMap<String, String>();
            geenHuis.put("error","Dit huis bestaat niet" );
            return Response.status(404).entity(geenHuis).build();
        }else{
            return Response.ok(newHuis).build();
        }
    }

    /*Response om gegevens van een huis te krijgen op basis van het huisId*/
    @GET
    @Path("/{id}")
    public Huis getHuis(@PathParam("id") int id){
        return service.findById(id);
    }

    /*Response om een lijst van alle huizen die in de database staan*/
    @GET
    public List<Huis> getAllHuizen(){
        return service.findAll();
    }

    /*Response om een Huis uit de database te verwijderen*/
    @DELETE
    @Path("/{id}")
    public Response deleteHuis(@PathParam("id") int id){
        Huis huis = service.findById(id);
        if(huis == null){
            Map<String, String> geenHuis = new HashMap<String, String>();
            geenHuis.put("error","Dit huis bestaat niet" );
            return Response.status(404).entity(geenHuis).build();
        } else {
            service.delete(huis);
            Map<String, String> deleteHuis = new HashMap<String, String>();
            deleteHuis.put("ok","Dit huis is verwijderd" );
            return Response.status(200).entity(deleteHuis).build();
        }
    }



}
