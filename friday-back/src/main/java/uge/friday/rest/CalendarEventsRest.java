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
    private ObjectMapper mapper = new ObjectMapper();

    @Path("getEvents/{year}/{month}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional

    //Imaginons que l'utilisateur veut voir les events de de decembre 2021 alors react va appeler cette
    //methode en donnant l'annee et le mois en parametre. Cette methode va devoir interoger la base de donnees
    //et renvoyer tout les events en forme de JSON en utilisant Jackson

    public String getEvents(@PathParam int year, @PathParam int month) throws JsonProcessingException {

        StringJoiner joiner = new StringJoiner(",","[","]");

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
    @GET
    @Transactional
    public void deleteEvent(@PathParam long id){
        repository.deleteEvent(id);
    }
    
    //Met a jour l'event dans la BDD. Il faut lire le Json envoye par react
    //Todo methode update in Event, the update method must define the fields to modify and set it
    
    @Path("updateEvent/{id}/{eventJson}")
    @GET
    @Transactional
    public void updateEvent(@PathParam long id, @PathParam String eventJson) throws JsonProcessingException {
        CalendarEvent event = mapper.readValue(eventJson, CalendarEvent.class);
        repository.updateEvent(id, event);
    }
    
    //Lis l'eventJson et le rajoute dans la BDD
    
    @Path("addEvent/{eventJson}")
    @GET
    @Transactional
    public void addEvent(@PathParam String eventJson) throws JsonProcessingException {
        CalendarEvent event = mapper.readValue(eventJson, CalendarEvent.class);
        repository.persist(event);
    }

    //Lis le string en format ical et rajoute les events dans la BDD
    
    @Path("sendIcal/{icalString}")
    @GET
    @Transactional
    public void addIcalEvents(@PathParam String icalString){

        System.out.println(icalString);
        IcalReader reader = new IcalReader();
        List<CalendarEvent> list = reader.readIcal(icalString);
        for(CalendarEvent event : list){
            repository.persist(event);
        }
    }


}
