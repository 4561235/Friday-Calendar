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
        this.sendToDelete = this.sendToDelete.bind(this);
        this.sendToAdd = this.sentToAdd.bind(this);
    }

    setModifyMode(boolean){
        this.setState({modifyMode: boolean});
    }

    sendToUpdate(){
        this.props.hidePopup();
        this.setModifyMode(false);

        let dateFromSplit = this.state.from.split("-");
        let dateToSplit = this.state.to.split("-");

        this.props.eventsManager.updateEvent(this.props.event.id, 
                                            dateFromSplit[0], dateFromSplit[1], dateFromSplit[2],
                                            dateToSplit[0], dateToSplit[1], dateToSplit[2],
                                            this.state.title, this.state.location, this.state.description, this.state.recurrence);

    }

    sendToDelete(event){
        this.props.hidePopup();
        this.props.eventsManager.deleteEvent(event);
    }

    sentToAdd(){
        this.props.hidePopup();
        let dateFromSplit = this.state.from.split("-");
        let dateToSplit = this.state.to.split("-");

        this.props.eventsManager.addEvent( dateFromSplit[0], dateFromSplit[1], dateFromSplit[2],
                                            dateToSplit[0], dateToSplit[1], dateToSplit[2],
                                            this.state.title, this.state.location, this.state.description, this.state.recurrence);
    }

    render(){
        return(
            <React.Fragment>
                {this.state.modifyMode === false && this.props.addingEventMode === false
    
                ? <div className="popup">
                    <button type="button" className="popup-btn-close" onClick={this.props.hidePopup}>X</button>
                    <p><b>{this.props.event.title}</b></p>
                    <p>{this.props.event.description}</p>
                    <p>{this.props.event.location}</p>
                    <p><b>From:</b> {this.props.event.from.day}/{this.props.event.from.month}/{this.props.event.from.year}</p>
                    <p><b>To:</b> {this.props.event.to.day}/{this.props.event.to.month}/{this.props.event.to.year}</p>
                    
                    <button type="button" className="popup-btn" onClick={() => this.setModifyMode(true)}>Modify</button>
                    <button type="button" className="popup-btn" onClick={() => this.sendToDelete(this.props.event)}>Delete</button>
                </div>
    
                :<div className="popup">
                    
                    
                    
                    <form onSubmit={e => {e.preventDefault(); if(this.props.addingEventMode) this.sendToAdd(); else this.sendToUpdate();}}>
                        {this.state.modifyMode ? <b>Modify Event</b> : <b>Add Event</b> }
                        <button type="button" className="popup-btn-close" onClick={this.props.hidePopup}>X</button>
                        

                        <p><b>Title:  </b>
                            <input className="text-input" type="text" required="required" title="Max 64 characteres" pattern="{1,64}" value={this.state.title} onChange={e => this.setState({title: e.target.value})}/>
                        </p>

                        <p><b>Description:  </b>
                            <input className="text-input" type="text" title="Max 128 characteres" pattern="{1,128}" value={this.state.description} onChange={e => this.setState({description: e.target.value})}/>
                        </p>

                        <p><b>Location:  </b>
                            <input className="text-input" type="text" title="Max 64 characteres" pattern="{1,64}" value={this.state.location} onChange={e => this.setState({location: e.target.value})}/>
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
                    </form> 
                </div>
                }
            </React.Fragment>
        );
    }
}

export default EventPopup;