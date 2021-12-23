import React, {Component} from "react";
import "./CalendarEvent.css";

class CalendarEvent extends Component{

    renderTimeIfNeeded = () =>{
        if(this.props.event.allDay === false){
            if(this.props.showFromTime){
                return this.props.event.from.time.hour +":" +this.props.event.from.time.minute +" ";
            }
            else if(this.props.showToTime){
                return this.props.event.to.time.hour +":" +this.props.event.to.time.minute +" ";
            }
        }
        return <React.Fragment></React.Fragment>
    }

    render(){
        return(
            <React.Fragment>
                <p className="event-title" onClick={() => this.props.showPopup(this.props.event)}>
                    {/* {this.props.event.allDay === "false"
                        ? {this.props.showFromTime ? this.props.event.from.time.hour +":" +this.props.event.from.time.minute +" " :<React.Fragment></React.Fragment>}
                        : {this.props.showToTime ? this.props.event.to.time.hour +":" +this.props.event.to.time.minute +" " :<React.Fragment></React.Fragment>}
                    } */}
                    {this.renderTimeIfNeeded()}
                    {this.props.event.title}
                </p>
                {/* <EventPopup key="popup" event={this.props.event}></EventPopup> */}
            </React.Fragment>
        );
    }
}

export default CalendarEvent;