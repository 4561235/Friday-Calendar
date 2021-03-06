import React, {Component} from "react";
import "./Day.css";
import CalendarEvent from "./CalendarEvent.jsx";

class Day extends Component{

    days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

    needShowFromTime(event){
        return event.from.day === this.props.day
    }

    needShowToTime(event){
        return event.to.day === this.props.day
    }

    render(){
        return(
            <td className="calendar-day">
                <p className="day-num">{this.props.day}</p>
                {/* <p className="dayName">{this.days[new Date(2021, 10, 4).getDay()]}</p> */}
                <br></br>
                {this.props.events.map(ev => 
                    <React.Fragment key={ev.id}>
                        <CalendarEvent key={ev.id} event={ev} showPopup={this.props.showPopup} showFromTime={this.needShowFromTime(ev)} showToTime={this.needShowToTime(ev)}></CalendarEvent>
                    </React.Fragment>)}
            </td>
        );
    }
}

export default Day;