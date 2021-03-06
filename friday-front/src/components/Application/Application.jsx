import React, {Component} from "react";
import EventsManager from "../EventsManager.js"
import Calendar from '../Calendar/Calendar.jsx';
import DaySummary from '../DaySummary/DaySummary.jsx';
import SubwayStatus from '../SubwayStatus/SubwayStatus.jsx';


class Application extends Component{

    eventsManager = new EventsManager();

    constructor(props){
        super(props);
        this.state = {
            calendarEvents: [],
            daySummaryEvents: []
        }

        this.fetchCalendarEvents = this.fetchCalendarEvents.bind(this);
        this.fetchDaySummaryEvents = this.fetchDaySummaryEvents.bind(this);
        this.refreshEventsAllApp = this.refreshEventsAllApp.bind(this);
    }


    fetchCalendarEvents(year, month){
        // console.log("Date: " +year +" " +month)
        var promise = this.eventsManager.getEvents(year,month);
        promise.then( result => {
            //console.log(result)
            this.setState({calendarEvents: result});
        }).catch(err => {
            console.log(err);
        });
    }

    fetchDaySummaryEvents(year, month){
        // console.log("Date: " +year +" " +month)
        var promise = this.eventsManager.getEvents(year,month);
        promise.then( result => {
            //console.log(result)
            this.setState({daySummaryEvents: result});
        }).catch(err => {
            console.log(err);
        });
    }

    refreshEventsAllApp(year, month){
        const date = new Date();
        this.fetchCalendarEvents(year, month);
        this.fetchDaySummaryEvents(date.getFullYear(), date.getMonth()+1);
    }



    render() {
        return(
        <React.Fragment>
            <SubwayStatus></SubwayStatus>
            
            <DaySummary fetchEvents={this.fetchDaySummaryEvents} 
                        events={this.state.daySummaryEvents}>
            </DaySummary>

            <Calendar fetchEvents={this.fetchCalendarEvents} 
                    events={this.state.calendarEvents} 
                    refreshEventsAllApp={this.refreshEventsAllApp} 
                    eventsManager={this.eventsManager}>
            </Calendar>

            

        </React.Fragment>
        );
    }
}

export default Application;