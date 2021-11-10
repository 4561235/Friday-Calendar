import React, {Component} from "react";
import "./CalendarEvent.css";

class CalendarEvent extends Component{

    render(){
        return(
            <React.Fragment>
                <p className="event-title" onClick={() => this.props.showPopup(this.props.event)}>{this.props.event.title}</p>
                {/* <EventPopup key="popup" event={this.props.event}></EventPopup> */}
            </React.Fragment>
        );
    }
}

export default CalendarEvent;