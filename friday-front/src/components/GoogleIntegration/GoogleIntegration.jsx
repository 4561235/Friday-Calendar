import React, {Component} from "react";
import "./GoogleIntegration.css";

class GoogleIntegration extends Component{

    constructor(props){
        super(props);

        this.importFromGoogleCalendar = this.importFromGoogleCalendar.bind(this);
    }

    importFromGoogleCalendar(){
        fetch("http://localhost:8080/calendar-events/connectToGoogleCalendar/" , {
            method: 'GET', // *GET, POST, PUT, DELETE, etc.
            })
        .then(() => this.props.refreshEvents())
        .catch(err => console.log(err));
    }


    render(){
        return(
            <div>
                <button className="btn" type="button" onClick={this.importFromGoogleCalendar}> Importer a partir de Google Calendar </button>
                
            </div>
        );
    }
}

export default GoogleIntegration;