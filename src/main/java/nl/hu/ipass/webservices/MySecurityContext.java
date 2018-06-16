package nl.hu.ipass.webservices;

import java.security.Principal;
import javax.ws.rs.core.SecurityContext;

public class MySecurityContext implements SecurityContext {
    private String email;
    private String role;
    private boolean isSecure;
    private int id;

    /*Constructor voor de MySecurutyContext.
    * De bestaande student heeft altijd een id hoger dan 1.
    * Dan krijgt de ingelogte student de rol student*/
    public MySecurityContext(String email, int id, boolean isSecure) {
        this.email = email;
        this.id = id;
        if(id > 1){
            this.role = "student";
        }
    }
    /*methode van de interface wordt geimplementeerd: */
    public Principal getUserPrincipal() {
        return new Principal() {
            public String getName() {
                return email;
            }
        };
    }

    /*Getter voor id van student: */
    public int getId(){
        return id;
    }

    /*Getter voor email van student: */
    public String getEmail(){
        return email;
    }

    /*Methodes van de interface; */
    public boolean isUserInRole(String role) {
        return role.equals(this.role);
    }
    public boolean isSecure() {
        return isSecure;
    }
    public String getAuthenticationScheme() {
        return "Bearer";
    }

}