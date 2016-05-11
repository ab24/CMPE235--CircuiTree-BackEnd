package org.glassfish.jersey.archetypes.apiresources;

import org.glassfish.jersey.archetypes.services.ServiceFactory;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Created by akhilakishore on 29/04/16.
 */

@Path("/adminlogin")
public class AdminLoginResource extends BaseApiResource{
    @GET
    @PermitAll
    @Produces
    public Response adminLogin(@QueryParam("adminId") String adminId, @QueryParam("password") String password) throws Exception{
         final String sessionToken = ServiceFactory.getServiceFactory().getAdminService().adminLogIn(adminId,password);
        if(sessionToken.equals("")){
            return genarate401Response("Unauthorized access");
        } else {
            return genarate200Response(sessionToken);
        }
    }


    public static void main(String args[]) throws Exception {
        new AdminLoginResource().adminLogin("akhila@gmail.com", "5678");
    }
}
