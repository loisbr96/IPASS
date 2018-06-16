package nl.hu.ipass.webservices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException{
        boolean isSecure = requestContext.getSecurityContext().isSecure();

        /*Gebruiker worden behandeld als gast, behalve als er een geldig JWT is gegeven: */
        MySecurityContext msc = new MySecurityContext("guest", -1, isSecure);

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring("Bearer".length()).trim();

            try {
                /*Valideren van het token: */
                JwtParser parser = Jwts.parser().setSigningKey(AuthenticationResource.key);
                Claims claims = parser.parseClaimsJws(token).getBody();

                String email = claims.getSubject();
                int id = Integer.parseInt(claims.get("id").toString());
                msc = new MySecurityContext(email, id, isSecure);

            } catch (JwtException | IllegalArgumentException e) {
                System.out.println("Invalid JWT, processing as guest!");
            }
        }
        requestContext.setSecurityContext(msc);
    }
}
