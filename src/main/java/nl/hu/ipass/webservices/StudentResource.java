package nl.hu.ipass.webservices;

import nl.hu.ipass.domain.Student;
import nl.hu.ipass.services.ServiceProvider;
import nl.hu.ipass.services.StudentService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/*Hier wordt de class bepaald en de studentservices aangeroepen: */
@Path("/student")
@RolesAllowed("student")
@Produces("application/json")
public class StudentResource {
    StudentService service = ServiceProvider.getStudentService();

    /*De response voor een nieuwe student wat de meegegeven waarden opslaat d.m.v. de save functie*/
    @POST
    @PermitAll
    public Response newStudent(@FormParam("voornaam") String voornaam,
                                @FormParam("tussenvoegsel") String tussenvoegsel,
                                @FormParam("achternaam") String achternaam,
                                @FormParam("email") String email,
                                @FormParam("wachtwoord") String wachtwoord){
        Student newStudent = service.save(new Student(voornaam, tussenvoegsel, achternaam, email, wachtwoord));
        return Response.ok(newStudent).build();
    }

    /*De Response voor het opvragen van een student.
    * Het vraagt d.m.v. de MySecurity het id van de ingelogte student op.
     * Het geeft met dit id de info van de student.*/
    @GET
    public Student getStudent(@Context ContainerRequestContext context) {
        MySecurityContext msc = (MySecurityContext) context.getSecurityContext();

        return service.findById(msc.getId());
    }

    /*Response op de student te updaten d.m.v. meegegeven waarden. */
    @PUT
    public Response updateStudent(@Context ContainerRequestContext context,
                                  @FormParam("voornaam") String voornaam,
                                  @FormParam("tussenvoegsel") String tussenvoegsel,
                                  @FormParam("achternaam") String achternaam,
                                  @FormParam("email") String email){
        MySecurityContext msc = (MySecurityContext) context.getSecurityContext();
        Student student = service.findById(msc.getId());
        student.setVoornaam(voornaam);
        student.setTussenvoegsel(tussenvoegsel);
        student.setAchternaam(achternaam);
        student.setEmail(email);

        service.update(student);
        Map<String, String> updateStudent = new HashMap<String, String>();
        updateStudent.put("ok","De student in geupdate" );
        return Response.status(200).entity(updateStudent).build();
    }

    /*Response om de student te verwijderen. Mag alleen zichzelf verwijderen.
    * Door middel van de ingelogde student wordt het id opgevraagd.
    * Deze wordt meegezonden in het verzoek.*/
    @DELETE
    public Response deleteStudent(@Context ContainerRequestContext context){
        MySecurityContext msc = (MySecurityContext) context.getSecurityContext();
        Student student = service.findById(msc.getId());
        if(student == null){
            Map<String, String> geenStudent = new HashMap<String, String>();
            geenStudent.put("error","Deze student bestaat niet" );
            return Response.status(404).entity(geenStudent).build();
        } else {
            Map<String, String> deleteStudent = new HashMap<String, String>();
            deleteStudent.put("ok","De student is verwijderd" );
            service.delete(student);
            return Response.status(200).entity(deleteStudent).build();
        }
    }



}
