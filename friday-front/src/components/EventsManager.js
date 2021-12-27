export default class EventsManager{

    deleteEvent(event){
        var url = "http://localhost:8080/calendar-events/deleteEvent/" + event.id;
        var promise = this.fetchInBackend(url);
        return promise;
    }

    addEvent(fromYear, fromMonth, fromDay, toYear, toMonth, toDay, fromHour, fromMinute, toHour, toMinute ,title, location, description, recurrence, allDay){
        let event = this.createBlankEvent(fromYear,fromMonth);
        
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

        event.from.time.hour = fromHour;
        event.from.time.minute = fromMinute;
        event.to.time.hour = toHour;
        event.to.time.minute = toMinute;

        event.allDay = allDay;

        var url = "http://localhost:8080/calendar-events/addEvent/" + JSON.stringify(event);
        var promise = this.fetchInBackend(url);
        return promise;
    }

    updateEvent(id, fromYear, fromMonth, fromDay, toYear, toMonth, toDay, fromHour, fromMinute, toHour, toMinute ,title, location, description, recurrence, allDay){
        let event = this.createBlankEvent(fromYear,fromMonth)

        // event.id = id
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

        event.from.time.hour = fromHour;
        event.from.time.minute = fromMinute;
        event.to.time.hour = toHour;
        event.to.time.minute = toMinute;

        event.allDay = allDay;


        var url = "http://localhost:8080/calendar-events/updateEvent/" +id +"/" + JSON.stringify(event);
        var promise = this.fetchInBackend(url);
        return promise;

    }

    getEvents(year, month){
        var url = "http://localhost:8080/calendar-events/getEvents/" + year + "/" +month;

        var promise = this.fetchInBackend(url)
            .then(response => response.json()).then( (user) => {
                var jsonStr = JSON.stringify(user);
                var temp = JSON.parse(jsonStr);
                return temp;
            });
  
          return promise;
    }

    fetchInBackend(url){
        var promise = fetch(url , {
          method: 'GET', // *GET, POST, PUT, DELETE, etc.
          mode: 'cors', // no-cors, *cors, same-origin
          cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
          credentials: 'same-origin', // include, *same-origin, omit
          headers: {
              'Content-Type': 'application/json'//,
              //'Authorization': `Bearer ${this.$jwt.getToken()}`
              // 'Content-Type': 'application/x-www-form-urlencoded',
          },
          redirect: 'follow', // manual, *follow, error
          referrerPolicy: 'no-referrer'
          })
        
          return promise;
    }

    createBlankEvent(year, month){
        let event = {"from":{"day":"01","month":month,"year":year,"time":{"hour":0,"minute":0}},
                    "to":{"day":"01","month":month,"year":year,"time":{"hour":0,"minute":0}},
                    "title":"","location":"","description":"","recurrence":"NONE","calendarType":"FRIDAY", "allDay":"false"};
        return event;
    }
}