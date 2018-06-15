package nl.hu.ipass.webservices;

import java.security.Principal;
import javax.ws.rs.core.SecurityContext;

public class MySecurityContext implements SecurityContext {
    private String email;
    private String role;
    private boolean isSecure;
    private int id;

    public MySecurityContext(String email, int id, boolean isSecure) {
        this.email = email;
        this.id = id;
        if(id > 1){
            this.role = "student";
        }
    }

    public Principal getUserPrincipal() {
        return new Principal() {
            public String getName() {
                return email;
            }
        };
    }

    public int getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

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