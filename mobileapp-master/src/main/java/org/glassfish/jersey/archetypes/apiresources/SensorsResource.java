package org.glassfish.jersey.archetypes.apiresources;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.archetypes.model.Sensor;
import org.glassfish.jersey.archetypes.services.ServiceFactory;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.text.SimpleDateFormat;

/**
 * Created by akhilakishore on 30/04/16.
 */
@Path("/sensors")
public class SensorsResource extends BaseApiResource {
    @GET
    @Path("/{sensorId}")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorBySensorId(@PathParam("sensorId") int sensorId, @QueryParam("sessionToken")String sessionToken) throws Exception {
        final Sensor sensor = ServiceFactory.getServiceFactory().getSensorService().getSensorBySensorId(sensorId, sessionToken);
        if (sensor == null || sensor.getSensorId() == 0) {
            return generate404Response("No sensor found");
        } else {
            return genarate200Response(sensor);
        }

    }

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSensorInformation(@QueryParam("sessionToken") String sessionToken)throws Exception {
        final JSONArray allSensors = ServiceFactory.getServiceFactory().getSensorService().getAllSensorInformation(sessionToken);
        if(allSensors==null){
            return generate404Response("No sensor found");
        } else {
            return genarate200Response(allSensors);
        }
    }

    @GET
    @Path("/delete/{sensorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSensor(@PathParam("sensorId") int sensorId, @QueryParam("sessionToken")String sessionToken) throws Exception {
        int result = ServiceFactory.getServiceFactory().getSensorService().deleteSensor(sensorId, sessionToken);
        if(result!=-1){
            return genarate200Response("Sensor was deleted successfully");
        }else {
            return generate404Response("Sensor was NOT deleted");
        }
    }

    @GET
    @Path("/update/{sensorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSensor(@PathParam("sensorId") int sensorId, @QueryParam("maintenanceDate") String maintenanceDate, @QueryParam("sessionToken") String sessionToken) throws Exception {
        int result = ServiceFactory.getServiceFactory().getSensorService().updateSensor(sensorId,maintenanceDate,sessionToken);
        if(result != -1){
            return genarate200Response("Tree maintenance date was updated Successfully");
        }else {
            return genarate401Response("UNSUCCESSFUL");
        }
    }

    @POST
    @PermitAll
    @Consumes({"application/json"})
    public Response addSensor(String sensorJson, @QueryParam("sessionToken") String sessionToken, @Context UriInfo uriInfo) throws Exception {
        Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        Sensor sensor = (Sensor)mapper.readValue(sensorJson, Sensor.class);
        int result = ServiceFactory.getServiceFactory().getSensorService().addSensor(sensor,sessionToken);
        if(result!=-1){
            return genarate200Response("SENSOR was inserted");
        }else {
            return genarate401Response("SENSOR was NOT installed");
        }
    }
}
