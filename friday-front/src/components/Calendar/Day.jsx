import React, {Component} from "react";
import "./Day.css";

class Day extends Component{

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
                {/* <br></br> */}
                {this.props.events.map(ev => <React.Fragment key={ev.id}><br></br>{ev.description}</React.Fragment>)}
            </td>
        );
    }
}

export default Day;