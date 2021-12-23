import React, {Component} from "react";
import "./CalendarEvent.css";

class CalendarEvent extends Component{

    prependZeroIfNeeded(value){
        var string = String(value)
        if(string.length === 1) string = "0" +string;
        return string;
    }

    renderTimeIfNeeded = () =>{
        if(this.props.event.allDay === false){
            if(this.props.showFromTime){
                return this.props.event.from.time.hour +":" +this.prependZeroIfNeeded(this.props.event.from.time.minute) +" ";
            }
            else if(this.props.showToTime){
                return this.props.event.to.time.hour +":" +this.prependZeroIfNeeded(this.props.event.to.time.minute) +" ";
            }
        }
        return <React.Fragment></React.Fragment>
    }

    

    render(){
        return(
            <React.Fragment>
                <p className="event-title" onClick={() => this.props.showPopup(this.props.event)}>
                    {this.renderTimeIfNeeded()}
                    {this.props.event.title}
                </p>
            </React.Fragment>
        );
    }
}

export default CalendarEvent;