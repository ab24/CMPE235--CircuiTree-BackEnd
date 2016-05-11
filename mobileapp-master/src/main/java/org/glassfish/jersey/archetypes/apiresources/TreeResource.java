package org.glassfish.jersey.archetypes.apiresources;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.archetypes.model.Tree;
import org.glassfish.jersey.archetypes.services.ServiceFactory;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by akhilakishore on 30/04/16.
 */
@Path("/tree")
public class TreeResource extends BaseApiResource {
    @GET
    @Path("/{treeId}")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTreeByTreeId(@PathParam("treeId") int treeId,@QueryParam("sessionToken")String sessionToken) throws Exception {
        final Tree tree = ServiceFactory.getServiceFactory().getTreeService().getTreeByTreeId(treeId, sessionToken);
        if(tree==null || tree.getTreeId() == 0){
            return generate404Response("Trees not Found");
        }else{
            return genarate200Response(tree);
        }
    }

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTreeInformation(@QueryParam("sessionToken")String sessionToken) throws Exception {
        final JSONArray allTrees = ServiceFactory.getServiceFactory().getTreeService().getAllTreeInformation(sessionToken);
        if(allTrees == null){
            return genarate401Response("No Trees found");
        }else{
            return genarate200Response(allTrees);
        }
    }

    @GET
    @Path("/delete/{treeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTree(@PathParam("treeId") int treeId, @QueryParam("sessionToken") String sessionToken) throws Exception {
        int result = ServiceFactory.getServiceFactory().getTreeService().deleteTree(treeId, sessionToken);
        if (result != -1) {
            return genarate200Response("Tree deleted");
        } else {
            return genarate401Response("Tree not deleted");
        }
    }

    @GET
    @Path("/update/{treeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTree(@PathParam("treeId") int treeId, @QueryParam("maintenanceDate") String maintenanceDate, @QueryParam("sessionToken") String sessionToken) throws Exception {
        int result = ServiceFactory.getServiceFactory().getTreeService().updateTree(treeId, maintenanceDate, sessionToken);
        if (result != -1) {
            return genarate200Response("Tree maintenance date updated");
        } else {
            return genarate401Response("Tree maintenance date not updated");
        }
    }

    @POST
    @PermitAll
    @Consumes({"application/json"})
    public Response addTree(String treeJson, @QueryParam("sessionToken") String sessionToken, @Context UriInfo uriInfo) throws Exception {
        Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        Tree tree = (Tree) mapper.readValue(treeJson, Tree.class);
        int result = ServiceFactory.getServiceFactory().getTreeService().addTree(tree, sessionToken);

        if (result != -1) {
            return genarate200Response("Tree inserted");
        } else {
            return genarate401Response("Tree not inserted");
        }
    }
}

