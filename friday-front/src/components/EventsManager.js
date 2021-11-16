export default class EventsManager{

     #events =  [
        {"id":1,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":15,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Vacances","location":"Paris","description":"Vacances a la mer","recurrence":"NONE","calendarType":"FRIDAY"},
        {"id":2,"from":{"day":20,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":23,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Projet","location":"Paris","description":"Faire projet java","recurrence":"NONE","calendarType":"FRIDAY"},
        {"id":3,"from":{"day":29,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":2,"month":12,"year":2021,"time":{"hour":10,"minute":30}},"title":"Repos","location":"Paris","description":"Je me repose","recurrence":"NONE","calendarType":"FRIDAY"},
        {"id":4,"from":{"day":2,"month":10,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Bruh","location":"Paris","description":"Le seum","recurrence":"NONE","calendarType":"FRIDAY"},
        {"id":5,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Overwatch","location":"Paris","description":"Jouer a Overwatch","recurrence":"NONE","calendarType":"FRIDAY"},
        {"id":6,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Sombra","location":"Paris","description":"Mainer sombra","recurrence":"NONE","calendarType":"FRIDAY"},
        {"id":7,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Sombra","location":"Paris","description":"Mainer sombra","recurrence":"NONE","calendarType":"FRIDAY"},
        {"id":8,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Sombra","location":"Paris","description":"Mainer sombra","recurrence":"NONE","calendarType":"FRIDAY"}
    ]


    getEvents(){
        return this.#events;
    }

    deleteEvent(event){
        let i = this.#events.indexOf(event);
        this.#events.splice(i,1);
    }

    addEvent(fromYear, fromMonth, fromDay, toYear, toMonth, toDay, title, location, description, recurrence){
        let event = {"id":this.#events.length+1,
                    "from":{"day":fromDay,"month":fromMonth,"year":fromYear,"time":{"hour":7,"minute":30}},
                    "to":{"day":toDay,"month":toMonth,"year":toYear,"time":{"hour":10,"minute":30}},
                    "title":title,"location":location,"description":description,"recurrence":recurrence,"calendarType":"FRIDAY"};
        
        this.#events.push(event);
    }

    updateEvent(id, fromYear, fromMonth, fromDay, toYear, toMonth, toDay, title, location, description, recurrence){
        let event = null;
        for (let index = 0; index < this.#events.length; index++) {
            const e = this.#events[index];
            if(e.id === id){event = e; break;}
        }
        if(event === null) return;

        event.from.year = fromYear;
        event.from.month = fromMonth;
        event.from.day = fromDay;
        event.to.year = toYear;
        event.to.month = toMonth;
        event.to.day = toDay;
        event.title = title;
        event.location = location;
        event.description = description;
        event.recurrence = recurrence;
    }

    createBlankEvent(year, month){
        let event = {"id":this.#events.length+1,
                    "from":{"day":"01","month":month,"year":year,"time":{"hour":7,"minute":30}},
                    "to":{"day":"01","month":month,"year":year,"time":{"hour":10,"minute":30}},
                    "title":"","location":"","description":"","recurrence":"NONE","calendarType":"FRIDAY"};
        return event;
    }
}