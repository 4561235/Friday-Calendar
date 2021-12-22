export default class EventsManager{

    //  #events =  [
    //     {"id":1,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":15,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Vacances","location":"Paris","description":"Vacances a la mer","recurrence":"NONE","calendarType":"FRIDAY"},
    //     {"id":2,"from":{"day":20,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":23,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Projet","location":"Paris","description":"Faire projet java","recurrence":"NONE","calendarType":"FRIDAY"},
    //     {"id":3,"from":{"day":29,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":2,"month":12,"year":2021,"time":{"hour":10,"minute":30}},"title":"Repos","location":"Paris","description":"Je me repose","recurrence":"NONE","calendarType":"FRIDAY"},
    //     {"id":4,"from":{"day":2,"month":10,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Bruh","location":"Paris","description":"Le seum","recurrence":"NONE","calendarType":"FRIDAY"},
    //     {"id":5,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Overwatch","location":"Paris","description":"Jouer a Overwatch","recurrence":"NONE","calendarType":"FRIDAY"},
    //     {"id":6,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Sombra","location":"Paris","description":"Mainer sombra","recurrence":"NONE","calendarType":"FRIDAY"},
    //     {"id":7,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Sombra","location":"Paris","description":"Mainer sombra","recurrence":"NONE","calendarType":"FRIDAY"},
    //     {"id":8,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Sombra","location":"Paris","description":"Mainer sombra","recurrence":"NONE","calendarType":"FRIDAY"}
    // ]

    // #refrechEventsFunc = null;

    // constructor(refreshEventsFunc){
    //     this.#refrechEventsFunc = refreshEventsFunc;
    // }

    // getEvents(year, month){
    //     var promise = this.fetchEvents(year, month)
    //     promise.then( result => {
    //         console.log(result)
    //         result.forEach(element => {
    //             this.#events.push(element)
    //         });
    //        }, function(error) {
    //         console.log(error)
    //        });
    
    //     return this.#events;
    // }

    deleteEvent(event){
        var promise = fetch("http://localhost:8080/calendar-events/deleteEvent/" + event.id , {
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

    addEvent(fromYear, fromMonth, fromDay, toYear, toMonth, toDay, title, location, description, recurrence){
        let event = this.createBlankEvent();
        
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

        //this.#events.push(event);

        var promise = fetch("http://localhost:8080/calendar-events/addEvent/" + JSON.stringify(event) , {
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

    updateEvent(id, fromYear, fromMonth, fromDay, toYear, toMonth, toDay, title, location, description, recurrence){
        // let event = null;
        // for (let index = 0; index < this.#events.length; index++) {
        //     const e = this.#events[index];
        //     if(e.id === id){event = e; break;}
        // }
        // if(event === null) return;

        let event = this.createBlankEvent(fromYear,fromMonth)

        event.id = id
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

        //console.log(JSON.stringify(event));

        var promise = fetch("http://localhost:8080/calendar-events/updateEvent/" + JSON.stringify(event) , {
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
        let event = {"id":0,
                    "from":{"day":"01","month":month,"year":year,"time":{"hour":7,"minute":30}},
                    "to":{"day":"01","month":month,"year":year,"time":{"hour":10,"minute":30}},
                    "title":"","location":"","description":"","recurrence":"NONE","calendarType":"FRIDAY"};
        return event;
    }

    fetchEvents(year, month){
        var promise = fetch("http://localhost:8080/calendar-events/getEvents/" + year + "/" +month, {
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
          }).then(response => response.json()).then( (user) => {
  
            var jsonStr = JSON.stringify(user);
            var temp = JSON.parse(jsonStr);
            return temp;
          });
  
          return promise;
    }
}