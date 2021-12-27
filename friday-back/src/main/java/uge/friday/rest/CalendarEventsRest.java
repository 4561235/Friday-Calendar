package uge.friday.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uge.friday.data.*;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Objects;
import java.util.StringJoiner;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.ws.rs.core.Response;

@Path("/calendar-events")
public class CalendarEventsRest {

    private PseudoTestDatabase testDatabase = new PseudoTestDatabase();

    @Path("getEvents/{year}/{month}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    //Imaginons que l'utilisateur veut voir les events de de decembre 2021 alors react va appeler cette
    //methode en donnant l'annee et le mois en parametre. Cette methode va devoir interoger la base de donnees
    //et renvoyer tout les events en forme de JSON en utilisant Jackson

    public String getEvents(@PathParam int year, @PathParam int month) throws JsonProcessingException {

        List<CalendarEvent> eventList = this.testDatabase.getEvents();

        StringJoiner joiner = new StringJoiner(",","[","]");
        ObjectMapper mapper = new ObjectMapper();
        //String json = mapper.writeValueAsString(event);

        for(int i = 0; i < eventList.size(); i++){
            CalendarEvent event = eventList.get(i);
            if(event.getFrom().getMonth() == month && event.getFrom().getYear() == year){
                String json = mapper.writeValueAsString(event);
                joiner.add(json);
            }
        }

        return joiner.toString();
    }
    
    //Supprime l'event de la BDD avec un id specifique
    
    @Path("deleteEvent/{id}")
    @DELETE
    @Transactional
    public Response deleteEvent(@PathParam int id){
        var event = CalendarEvent.findById(id);
        if (event == null) return Response.status(Response.Status.NOT_ACCEPTABLE).entity(id).build();
        event.delete();
        return Response.status(Response.Status.ACCEPTED).entity(id).build();
    }
    
    //Met a jour l'event dans la BDD. Il faut lire le Json envoye par react
    //Todo methode update in Event, the update method must define the fields to modify and set it
    
    @Path("updateEvent/{id}")
    @PUT
    @Transactional
    public Response updateEvent(@PathParam String id, CalendarEvent eventJson){
        Objects.requireNonNull(eventJson);
        var event = CalendarEvent.findById(id);
        if (event == null) return Response.status(Response.Status.NOT_ACCEPTABLE).entity(id).build();
        //event.update(CalendarEvent eventJson);
        return Response.status(Response.Status.ACCEPTED).entity(id).build();
    }
    
    //Lis l'eventJson et le rajoute dans la BDD
    
    @Path("addEvent/{eventJson}")
    @GET
    @Transactional
    public Response addEvent(CalendarEvent eventJson){
        Objects.requireNonNull(eventJson);
        eventJson.persist();
        return Response.status(Response.Status.CREATED).entity(eventJson).build();
    }

    //Lis le string en format ical et rajoute les events dans la BDD
    
    @Path("sendIcal/{icalString}")
    @GET
    public void addIcalEvents(@PathParam String icalString){
    	System.out.println(icalString);
    }


}
