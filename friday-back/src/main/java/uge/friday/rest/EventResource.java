package uge.friday.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.StringJoiner;

@Path("/events")
public class EventResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Get all of all event. String List<Event>
     */
    public String events() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        StringJoiner joiner = new StringJoiner(",","[","]\n");
        for (var e: Event.listAll()) {
            String json = mapper.writeValueAsString(e);
            joiner.add(json);
        }
        return joiner.toString();

        //return Event.listAll();
        //List<Event> events = Event.listAll();
        //return events.stream().toList();
    }


    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveEvent(Event event) {
        event.persist();
        return Response.status(Response.Status.CREATED).entity(event).build();
    }

    //  @PathParam String jsonStringEvent


    @POST
    @Path("/newEvent/{jsonStringEvent}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Transactional
    public Response newEvent(@PathParam String jsonStringEvent) throws JsonProcessingException {
        // Map jsonString to Event.

        ObjectMapper mapper = new ObjectMapper();
        //Event event = mapper.readValue(jsonStringEvent, Event.class);
        Event event = new Event();


        // Add event to bdd.
        event.persist();


        //return Response.status(Response.Status.CREATED).entity(event).build();

        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test/{jsonStringEvent}")
    public String events(@PathParam String jsonStringEvent) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Event event = mapper.readValue(jsonStringEvent, Event.class);
        return event.toString();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test2/{jsonStringEvent}")
    @Transactional
    public String testadd(@PathParam String jsonStringEvent) throws JsonProcessingException {
        Event event = new Event();
        event.title = "test 1milliars";
        event.persist();
        return event.toString();
    }


}