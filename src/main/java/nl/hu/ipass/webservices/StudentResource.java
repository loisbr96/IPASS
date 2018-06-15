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

@Path("/student")
@RolesAllowed("student")
@Produces("application/json")
public class StudentResource {
    StudentService service = ServiceProvider.getStudentService();

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

    @GET
    public Student getStudent(@Context ContainerRequestContext context) {
        MySecurityContext msc = (MySecurityContext) context.getSecurityContext();

        return service.findById(msc.getId());
    }

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
        return Response.status(200).build();
    }


    @DELETE
    public Response deleteStudent(@Context ContainerRequestContext context){
        MySecurityContext msc = (MySecurityContext) context.getSecurityContext();
        Student student = service.findById(msc.getId());
        if(student == null){
            return Response.status(404).build();
        } else {
            service.delete(student);
            return Response.status(200).build();
        }
    }



}
