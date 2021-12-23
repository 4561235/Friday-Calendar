import React, {Component} from "react";
import EventsManager from "../EventsManager.js"
import "./DaySummary.css";

class DaySummary extends Component{

    eventsManager = new EventsManager();

    constructor(props){
        super(props)
        this.state ={
            dayEvents: []
        };
    }

    componentDidMount(){
        const date = new Date();
        this.fetchEvents( date.getFullYear(), date.getMonth()+1)
        
    }

    fetchEvents(year, month){
        // console.log("Date: " +year +" " +month)
        var promise = this.eventsManager.getEvents(year, month)
        promise.then( result => {
            this.setState({dayEvents: result});
            //console.log(result)
        }).catch(err => {
            console.log(err);
        });
        
    }

    prependZeroIfNeeded(value){
        var string = String(value)
        if(string.length === 1) string = "0" +string;
        return string;
    }

    renderEventsList = () =>{
        const date = new Date();
        let retTab = [];
        let dayEventTab = [];

        for (let index = 0; index < this.state.dayEvents.length; index++){
            let ev = this.state.dayEvents[index];
            if(ev.from.day <= date.getDate()-1 && ev.to.day >= date.getDate()-1 ){
                dayEventTab.push(ev);
            }
        }

        dayEventTab.sort((ev1, ev2) => {
            if(ev1.from.time.hour  <= ev2.from.time.hour) return -1;
            else return 1;
        });

        for (let index = 0; index < dayEventTab.length; index++){
            let ev = dayEventTab[index];
            retTab.push(<li key={index} className="day-event">
                        <b>{ev.title}</b> <br></br>
                        {ev.location} <br></br>
                        {ev.from.time.hour +":" +this.prependZeroIfNeeded(ev.from.time.minute) +" - " +ev.to.time.hour +":"+this.prependZeroIfNeeded(ev.to.time.minute)}
                    </li>);
        }
        
        return retTab;
    }


    render(){
        return(
            <div  align="center">
                <h3><b>Resumé du jour :</b></h3>
                <ul>
                    <React.Fragment>
                        {this.renderEventsList()}
                    </React.Fragment>
                </ul>
            </div>
        );
    }
}

export default DaySummary;