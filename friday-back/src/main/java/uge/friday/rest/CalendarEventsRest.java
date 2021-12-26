package uge.friday.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uge.friday.data.*;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.StringJoiner;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

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
    @GET
    public void deleteEvent(@PathParam int id){
        this.testDatabase.removeEvent(id);
    }
    
    //Met a jour l'event dans la BDD. Il faut lire le Json envoye par react
    
    @Path("updateEvent/{eventJson}")
    @GET
    public void updateEvent(@PathParam String eventJson){
    	System.out.println(eventJson);
    }
    
    //Lis l'eventJson et le rajoute dans la BDD
    
    @Path("addEvent/{eventJson}")
    @GET
    public void addEvent(@PathParam String eventJson){
    	System.out.println(eventJson);
    }
    
    //Lis le string en format ical et rajoute les events dans la BDD
    
    @Path("sendIcal/{icalString}")
    @GET
    public void addIcalEvents(@PathParam String icalString){
    	System.out.println(icalString);
    }
    

}
