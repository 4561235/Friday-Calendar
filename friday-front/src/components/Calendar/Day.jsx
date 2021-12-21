import React, {Component} from "react";
import "./Day.css";
import CalendarEvent from "./CalendarEvent.jsx";

class Day extends Component{

    days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

    render(){
        return(
            <td className="calendar-day">
                <p className="day-num">{this.props.day}</p>
                {/* <p className="dayName">{this.days[new Date(2021, 10, 4).getDay()]}</p> */}
                <br></br>
                {this.props.events.map(ev => 
                    <React.Fragment key={ev.id}>
                        <CalendarEvent key={ev.id} event={ev} showPopup={this.props.showPopup}></CalendarEvent>
                    </React.Fragment>)}
            </td>
        );
    }
}

export default Day;