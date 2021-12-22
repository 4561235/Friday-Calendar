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
     * Get all of all event.
     */
    public String events() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        StringJoiner joiner = new StringJoiner(",","[","]");
        for (var e: Event.listAll()) {
            String json = mapper.writeValueAsString(e);
            joiner.add(json);
        }
        return joiner.toString();
        //return Event.listAll();
        //List<Event> events = Event.listAll();
        //return events.stream().toList();
    }

    @Path("postEvents/{title}")
    @POST
    @Transactional
    public static Response saveEvent(@PathParam String title) throws JsonProcessingException {
        var jsonStr = "{ \"title\" : \""+title+"\" }";
        ObjectMapper mapper = new ObjectMapper();
        Event event = mapper.readValue(jsonStr, Event.class);
        event.persist();
        System.out.println("Done persist.");
        return Response.status(Response.Status.CREATED).entity(event).build();
    }

    public static void main(String[] args) throws JsonProcessingException {
        //var jsonStr = "{\"id\":2,\"from\":{\"day\":20,\"month\":11,\"year\":2021,\"time\":{\"hour\":7,\"minute\":30}},\"to\":{\"day\":23,\"month\":11,\"year\":2021,\"time\":{\"hour\":10,\"minute\":30}},\"title\":\"Projet\",\"location\":\"Paris\",\"description\":\"Faire projet java\",\"recurrence\":\"NONE\",\"calendarType\":\"FRIDAY\"}"

        //var jsonStr = "{\"title\" : \"test_event_1\", \"description\" : \"stp fonctionne\"}";

        var jsonStr = "{ \"title\" : \"test_event\" }";
        System.out.println(jsonStr);

        ObjectMapper mapper = new ObjectMapper();
        Event event = mapper.readValue(jsonStr, Event.class);


        //var eRes = new EventResource();
        System.out.println(event);
        System.out.println(event.toString());

        //saveEvent(event);


    }

}