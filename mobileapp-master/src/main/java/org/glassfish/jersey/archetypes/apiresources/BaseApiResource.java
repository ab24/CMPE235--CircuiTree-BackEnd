package org.glassfish.jersey.archetypes.apiresources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.core.Response;

/**
 * Created by akhilakishore on 29/04/16.
 */
public abstract class BaseApiResource {
    protected Response generate404Response(String message) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return Response.status(404).entity(gson.toJson(message)).build();
    }

    protected Response genarate200Response(Object rawResponse) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return Response.status(200).entity(gson.toJson(rawResponse)).build();
    }

    protected Response genarate401Response(Object rawResponse) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return Response.status(401).entity(gson.toJson(rawResponse)).build();
    }
}
