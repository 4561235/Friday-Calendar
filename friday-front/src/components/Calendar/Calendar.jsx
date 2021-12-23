import React, {Component} from "react";
import Day from "./Day.jsx";
import "./Calendar.css";
import EventsManager from "../EventsManager.js"

import EventPopup from "./EventPopup.jsx";

class Calendar extends Component{

    monthToString = {1:"Janvier", 2:"Fevrier", 3:"Mars", 4:"Avril", 5:"Mai", 6:"Juin", 7:"Juillet", 8:"Aout", 9:"Septembre", 10:"Octobre", 11:"Novembre", 12:"Decembre"}
    eventsManager = new EventsManager();

    constructor(props){
        super(props);
        const date = new Date();

        this.state = {
            events: [],
            month: date.getMonth()+1,
            year: date.getFullYear(),

            isPopup: false,
            addingEventMode: false,
            popupEvent: null

        }
        
        this.addMonth = this.addMonth.bind(this);
        this.subMonth = this.subMonth.bind(this);
        this.addYear = this.addYear.bind(this);
        this.subYear = this.subYear.bind(this);

        this.hideEventPopup = this.hideEventPopup.bind(this);
        this.showEventPopup = this.showEventPopup.bind(this);

        this.addEvent = this.addEvent.bind(this);
        this.refreshEvents = this.refreshEvents.bind(this);
    }

    componentDidMount(){
        this.fetchEvents(this.state.year, this.state.month)
    }

    fetchEvents(year, month){
        // console.log("Date: " +year +" " +month)
        var promise = this.eventsManager.getEvents(year, month)
        promise.then( result => {
            //console.log(result)
            this.setState({events: result});
        }).catch(err => {
            console.log(err);
        });
    }

    refreshEvents(){
        // console.log("OK nice")
        // console.log("Date: " +this.state.year +" " +this.state.month)
        this.fetchEvents(this.state.year, this.state.month);
    }

    daysInMonth(month, year) {
        return new Date(year, month, 0).getDate();
    }

    showEventPopup(event){
        this.setState({isPopup: true});
        this.setState({popupEvent: event});
    }

    hideEventPopup(){
        this.setState({isPopup: false});
        this.setState({addingEventMode: false});
        // this.refreshEvents();
    }

    addEvent(){
        this.setState({addingEventMode: true});
        this.showEventPopup(this.eventsManager.createBlankEvent(this.state.year, this.state.month));
    }


    renderCalendar = () => {
        let tab = Array.from(Array(this.daysInMonth(this.state.month, this.state.year)).keys());
        let retTab = [];
        let accTab = [];
        
        //0=Sunday 1=Monday 6=Saturday
        let placeholderNumber = new Date(this.state.year, this.state.month-1, 1).getDay();
        let arePlaceholdersPushed = false;
                
        for (let index = 0; index < tab.length; index++) {
            if (accTab.length === 7) {
                //Push a row
                retTab.push(<tr key={"Row_" +index}>{accTab}</tr>);
                accTab=[];
            }
            
            let dayEvents = [];
            this.state.events.forEach(event => {
                if( new Date(event.from.year, event.from.month-1, event.from.day) <= new Date(this.state.year, this.state.month-1, tab[index]+1) &&
                    new Date(event.to.year, event.to.month-1, event.to.day) >= new Date(this.state.year, this.state.month-1, tab[index]+1)){
                        dayEvents.push(event);
                    }
                });
            
            if(!arePlaceholdersPushed){
                for(let i = 0; i < placeholderNumber; i++){
                    //Push placeholder
                    accTab.push(<td key={"Placeholder_"+i} className="day-placeholder"></td>);
                }
                arePlaceholdersPushed = true;
                index--;
            }
            else{
                //Push the day
                accTab.push(<Day key={"Day_"+index} day={tab[index]+1} events={dayEvents} showPopup={this.showEventPopup}></Day>);
            }
        }
        //Push remaining row
        retTab.push(<tr key={"Remaining_Row"}>{accTab}</tr>);
        return retTab;
      }

      addMonth(){
          this.hideEventPopup();
          if(this.state.month === 12){
              this.setState({month: 1});
              this.setState({year: this.state.year+1});
              this.fetchEvents(this.state.year+1, 1)
            }
            else{
                this.setState({month: this.state.month+1});
                this.fetchEvents(this.state.year, this.state.month+1)
            }
      }

      subMonth(){
        this.hideEventPopup();
        if(this.state.month === 1){
            this.setState({month: 12});
            this.setState({year: this.state.year-1});
            this.fetchEvents(this.state.year-1, 12)
        }
        else{
            this.setState({month: this.state.month-1});
            this.fetchEvents(this.state.year, this.state.month-1)
        }
      }

      addYear(){
        this.hideEventPopup();
        this.setState({year: this.state.year+1});
        this.fetchEvents(this.state.year+1, this.state.month)
      }

      subYear(){
        this.hideEventPopup();
        this.setState({year: this.state.year-1});
        this.fetchEvents(this.state.year-1, this.state.month)
      }

    render(){
        return (
            <div align="center">
                <h4 className="calendar-month">{this.monthToString[this.state.month] +" " +this.state.year}</h4>
                <button className="calendar-btn" onClick={this.subMonth}>{"<-"}</button>
                <button className="calendar-btn" onClick={this.addMonth}>{"->"}</button>
                <button className="calendar-btn" onClick={this.subYear}>{"<="}</button>
                <button className="calendar-btn" onClick={this.addYear}>{"=>"}</button>
                <button className="calendar-btn" onClick={this.addEvent}>+</button>
                <table>
                    <tbody>
                        <tr>
                            <td className="calendar-dayName">Dimanche</td>
                            <td className="calendar-dayName">Lundi</td>
                            <td className="calendar-dayName">Mardi</td>
                            <td className="calendar-dayName">Mercredi</td>
                            <td className="calendar-dayName">Jeudi</td>
                            <td className="calendar-dayName">Vendredi</td>
                            <td className="calendar-dayName">Samedi</td>
                        </tr>
                        {this.renderCalendar()}
                    </tbody>
                </table>
                {this.state.isPopup === true
                    ? <EventPopup key={"popup" +this.state.addingEventMode}
                                event={this.state.popupEvent}
                                hidePopup={this.hideEventPopup} 
                                addingEventMode={this.state.addingEventMode} 
                                eventsManager={this.eventsManager} 
                                month={this.state.month} year={this.state.year}
                                refreshEvents={this.refreshEvents}> 
                    </EventPopup>
                    : <React.Fragment></React.Fragment>
                }
            </div>
        );
    }
}

export default Calendar;
