package nl.hu.ipass.webservices;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.ipass.domain.Student;

import nl.hu.ipass.services.ServiceProvider;
import nl.hu.ipass.services.StudentService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Calendar;

@Path("/authentication")
public class AuthenticationResource {
    final static public Key key = MacProvider.generateKey();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("email") String email,
                                     @FormParam("wachtwoord") String wachtwoord) {
        try {
            /*Authenticate de gebruiker tegen de database*/
            StudentService studentService = ServiceProvider.getStudentService();
            System.out.println(email);
            System.out.println(DigestUtils.sha256Hex(wachtwoord));
            Student student = studentService.findByEmailAndWachtwoord(email,DigestUtils.sha256Hex(wachtwoord));

            if (student == null) { throw new IllegalArgumentException("No student found!"); }

            String token = createToken(student.getEmail(), student.getId());
            AbstractMap.SimpleEntry<String, String> JWT = new AbstractMap.SimpleEntry<String, String>("JWT", token);
            return Response.ok(JWT).build();

        } catch (JwtException | IllegalArgumentException e)
        { return Response.status(Response.Status.UNAUTHORIZED).build(); }
    }
    /*Hier wordt het token gemaakt en terug gegeven: */
    private String createToken(String email, int id) throws JwtException {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expiration.getTime())
                .claim("id", id)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}