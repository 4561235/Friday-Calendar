import React, {Component} from "react";
import "./DaySummary.css";

class DaySummary extends Component{

    componentDidMount(){
        const date = new Date();
        this.fetchEvents( date.getFullYear(), date.getMonth()+1)
        
    }

    fetchEvents(year, month){
        this.props.fetchEvents(year,month);
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


        for (let index = 0; index < this.props.events.length; index++){
            let ev = this.props.events[index];
            if(ev.from.day <= date.getDate() && ev.to.day >= date.getDate() ){
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

        if(retTab.length === 0) retTab.push(<b key={"no_event"} >Pas d'evenements aujourd'hui</b>)
        
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
                <br></br>
            </div>
        );
    }
}

export default DaySummary;