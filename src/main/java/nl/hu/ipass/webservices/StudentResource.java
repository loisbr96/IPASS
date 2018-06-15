package nl.hu.ipass.webservices;

import nl.hu.ipass.domain.Student;
import nl.hu.ipass.services.ServiceProvider;
import nl.hu.ipass.services.StudentService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/student")
@RolesAllowed("student")
@Produces("application/json")
public class StudentResource {
    StudentService service = ServiceProvider.getStudentService();

    @POST
    public Response newStudent(@FormParam("voornaam") String voornaam,
                                @FormParam("tussenvoegsel") String tussenvoegsel,
                                @FormParam("achternaam") String achternaam,
                                @FormParam("email") String email,
                                @FormParam("wachtwoord") String wachtwoord){
        Student newStudent = service.save(new Student(voornaam, tussenvoegsel, achternaam, email, wachtwoord));
        return Response.ok(newStudent).build();
    }

    @GET
    @Path("/{id}")
    public Student getStudent(@PathParam("id") int id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response updateStudent(@PathParam("id") int id,
                                  @FormParam("voornaam") String voornaam,
                                  @FormParam("tussenvoegsel") String tussenvoegsel,
                                  @FormParam("achternaam") String achternaam,
                                  @FormParam("email") String email){
        Student student = service.findById(id);
        student.setVoornaam(voornaam);
        student.setTussenvoegsel(tussenvoegsel);
        student.setAchternaam(achternaam);
        student.setEmail(email);

        service.update(student);
        return Response.status(200).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") int id){
        Student student = service.findById(id);
        if(student == null){
            return Response.status(404).build();
        } else {
            service.delete(student);
            return Response.status(200).build();
        }
    }



}
