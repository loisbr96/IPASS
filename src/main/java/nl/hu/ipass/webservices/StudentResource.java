package nl.hu.ipass.webservices;

import nl.hu.ipass.domain.Student;
import nl.hu.ipass.services.ServiceProvider;
import nl.hu.ipass.services.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/student")
public class StudentResource {
    StudentService service = ServiceProvider.getStudentService();

    @POST
    @Produces("applicatinon/json")
    public Response saveStudent(@FormParam("voornaam") String voornaam,
                                @FormParam("tussenvoegsel") String tussenvoegsel,
                                @FormParam("achternaam") String achternaam,
                                @FormParam("email") String email,
                                @FormParam("wachtwoord") String wachtwoord){
        Student newStudent = service.save(new Student(voornaam, tussenvoegsel, achternaam, email, wachtwoord));
        return Response.ok(newStudent).build();
    }

    @GET

    @Produces("application/json")
    public Student getStudent(){
        return service.findById();
        /*Hier verder werken*/
    }


}
