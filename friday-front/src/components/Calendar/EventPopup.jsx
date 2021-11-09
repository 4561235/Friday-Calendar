import React, {Component} from "react";
import "./EventPopup.css";

class EventPopup extends Component{


    render(){
        return(
            <div className="popup">
                <button className="popup-btn-close" onClick={this.props.hidePopup}>X</button>
                <p><b>Title:</b> {this.props.event.title}</p>
                <p><b>Description:</b> {this.props.event.description}</p>
                <p><b>Location:</b> {this.props.event.location}</p>
                <p><b>From:</b> {this.props.event.from.day}/{this.props.event.from.month}/{this.props.event.from.year}</p>
                <p><b>To:</b> {this.props.event.to.day}/{this.props.event.to.month}/{this.props.event.to.year}</p>
                
                <button className="popup-btn">Modify</button>
                <button className="popup-btn" onClick={() => this.props.deleteEvent(this.props.event)}>Delete</button>
            </div>
        );
    }
}

export default EventPopup;