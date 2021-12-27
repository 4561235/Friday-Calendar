package uge.friday.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.config.SysPropConfigSource;
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
    private CalendarEventRepository repository = new CalendarEventRepository();

    @Path("getEvents/{year}/{month}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional

    //Imaginons que l'utilisateur veut voir les events de de decembre 2021 alors react va appeler cette
    //methode en donnant l'annee et le mois en parametre. Cette methode va devoir interoger la base de donnees
    //et renvoyer tout les events en forme de JSON en utilisant Jackson

    public String getEvents(@PathParam int year, @PathParam int month) throws JsonProcessingException {

        StringJoiner joiner = new StringJoiner(",","[","]");
        ObjectMapper mapper = new ObjectMapper();

//        CalendarEvent ev1 = new CalendarEvent(
//                new CalendarDate(22,12,2021, new CalendarTime(7,30)),
//                new CalendarDate(23,12,2021, new CalendarTime(10,30)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Poire","Paris", "Je test desc", false);
//
//        CalendarEvent ev2 = new CalendarEvent(
//                new CalendarDate(25,12,2021, new CalendarTime(7,30)),
//                new CalendarDate(28,12,2021, new CalendarTime(10,45)),
//                EventRecurrenceEnum.NONE,
//                CalendarTypeEnum.FRIDAY,
//                "Lichi","Paris", "Je test desc", false);

//        repository.persist(ev1);
//        repository.persist(ev2);

        List<CalendarEvent> eventsList = repository.getEvents();
        System.out.println(eventsList.size());

        for(int i = 0; i < eventsList.size(); i++){
            CalendarEvent event = eventsList.get(i);
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
    public void addEvent(@PathParam String eventJson){

    }

    //Lis le string en format ical et rajoute les events dans la BDD
    
    @Path("sendIcal/{icalString}")
    @GET
    public void addIcalEvents(@PathParam String icalString){
    	System.out.println(icalString);
    }


}
