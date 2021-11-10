import React, {Component} from "react";
import "./EventPopup.css";

class EventPopup extends Component{

    constructor(props){
        super(props);

        //HTML date need a "0" before a day otherwise the date won't display
        let adjustFromDay = String(this.props.event.from.day);
        if(adjustFromDay.length === 1) adjustFromDay = "0" +adjustFromDay

        let adjustToDay = String(this.props.event.to.day);
        if(adjustToDay.length === 1) adjustToDay = "0" +adjustToDay

        this.state = {
            modifyMode: false,

            title: this.props.event.title,
            description: this.props.event.description,
            location: this.props.event.location,
            from: this.props.event.from.year +"-" +this.props.event.from.month +"-" +adjustFromDay,
            to: this.props.event.to.year +"-" +this.props.event.to.month +"-" +adjustToDay,
            recurrence: this.props.event.recurrence
        }

        this.setModifyMode = this.setModifyMode.bind(this);
        this.sendToUpdate = this.sendToUpdate.bind(this);
    }

    setModifyMode(boolean){
        this.setState({modifyMode: boolean});
    }

    sendToUpdate(){
        this.setModifyMode(false);
        
        let updEvent = this.props.event;
        updEvent.title = this.state.title;
        updEvent.description = this.state.description;
        updEvent.location = this.state.location;

        let dateFromSplit = this.state.from.split("-");
        let dateToSplit = this.state.to.split("-");
        
        updEvent.from.year = dateFromSplit[0];
        updEvent.from.month = dateFromSplit[1];
        updEvent.from.day = dateFromSplit[2];

        updEvent.to.year = dateToSplit[0];
        updEvent.to.month = dateToSplit[1];
        updEvent.to.day = dateToSplit[2];
        
        updEvent.recurrence = this.state.recurrence;

        this.props.hidePopup();
    }


    render(){
        return(
            <React.Fragment>
                {this.state.modifyMode === false
    
                ? <div className="popup">
                    <button className="popup-btn-close" onClick={this.props.hidePopup}>X</button>
                    <p><b>Title:</b> {this.props.event.title}</p>
                    <p><b>Description:</b> {this.props.event.description}</p>
                    <p><b>Location:</b> {this.props.event.location}</p>
                    <p><b>From:</b> {this.props.event.from.day}/{this.props.event.from.month}/{this.props.event.from.year}</p>
                    <p><b>To:</b> {this.props.event.to.day}/{this.props.event.to.month}/{this.props.event.to.year}</p>
                    
                    <button className="popup-btn" onClick={() => this.setModifyMode(true)}>Modify</button>
                    <button className="popup-btn" onClick={() => this.props.deleteEvent(this.props.event)}>Delete</button>
                </div>
    
                : <form onSubmit={this.sendToUpdate}>
                    <div className="popup">
                        <button className="popup-btn-close" onClick={this.props.hidePopup}>X</button>

                        <p><b>Title:  </b>
                            <input className="text-input" type="text" required="required" title="Max 64 characteres" pattern="[A-Za-z0-9 ]{1,64}" value={this.state.title} onChange={e => this.setState({title: e.target.value})}/>
                        </p>

                        <p><b>Description:  </b>
                            <input className="text-input" type="text" title="Max 128 characteres" pattern="[A-Za-z0-9 ]{1,128}" value={this.state.description} onChange={e => this.setState({description: e.target.value})}/>
                        </p>

                        <p><b>Location:  </b>
                            <input className="text-input" type="text" title="Max 64 characteres" pattern="[A-Za-z0-9 ]{1,64}" value={this.state.location} onChange={e => this.setState({location: e.target.value})}/>
                        </p>

                        <p><b>From: </b>
                            <input className="date-input" type="date" max={this.state.to} value={this.state.from} onChange={e => this.setState({from: e.target.value})}/>
                        </p>

                        <p><b>To: </b>
                            <input className="date-input" type="date" min ={this.state.from} value={this.state.to} onChange={e => this.setState({to: e.target.value})}/>
                        </p>

                        <p><b>Recurrence: </b>
                            <select value={this.state.recurrence} onChange={e => this.setState({recurrence: e.target.value})}>
                                <option value={this.state.recurrence}>{this.state.recurrence}</option>
                                <option value="DAILY">DAILY</option>
                                <option value="MONTHLY">MONTHLY</option>
                                <option value="YEARLY">YEARLY</option>
                                <option value="NONE">NONE</option>
                            </select>
                        </p>


                        <input type="submit" className="popup-btn" value="Save"/>
                    </div>
                </form> 
                }
            </React.Fragment>
        );
    }
}

export default EventPopup;