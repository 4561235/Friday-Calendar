import React, {Component} from "react";
import "./Day.css";

class Day extends Component{

    days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    componentDidMount(){
        //console.log("Day " +this.state.day);
        //console.log(this.state.events);
    }

    componentDidUpdate() {
  
    }


    render(){
        return(
            <td className="calendar-day">
                <p className="day-num">{this.props.day}</p>
                {/* <p className="dayName">{this.days[new Date(2021, 10, 4).getDay()]}</p> */}
                {/* <br></br> */}
                {this.props.events.map(ev => <React.Fragment key={ev.id}><br></br>{ev.description}</React.Fragment>)}
            </td>
        );
    }
}

export default Day;