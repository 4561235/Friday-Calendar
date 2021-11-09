import React, {Component} from "react";
import Day from "./Day.jsx";
import "./Calendar.css";

import EventPopup from "./EventPopup.jsx";

class Calendar extends Component{

    monthToString = {1:"Janvier", 2:"Fevrier", 3:"Mars", 4:"Avril", 5:"Mai", 6:"Juin", 7:"Juillet", 8:"Aout", 9:"Septembre", 10:"Octobre", 11:"Novembre", 12:"Decembre"}
    

    constructor(props){
        super(props)
        //const date = new Date();
        this.state = {
            events: [
                {"id":1,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":15,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Vacances","location":"Paris","description":"Vacances a la mer","recurrence":"NONE","calendarType":"FRIDAY"},
                {"id":2,"from":{"day":20,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":23,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Projet","location":"Paris","description":"Faire projet java","recurrence":"NONE","calendarType":"FRIDAY"},
                {"id":3,"from":{"day":29,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":2,"month":12,"year":2021,"time":{"hour":10,"minute":30}},"title":"Repos","location":"Paris","description":"Je me repose","recurrence":"NONE","calendarType":"FRIDAY"},
                {"id":4,"from":{"day":2,"month":10,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Bruh","location":"Paris","description":"Le seum","recurrence":"NONE","calendarType":"FRIDAY"},
                {"id":5,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Overwatch","location":"Paris","description":"Jouer a Overwatch","recurrence":"NONE","calendarType":"FRIDAY"},
                {"id":6,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Sombra","location":"Paris","description":"Mainer sombra","recurrence":"NONE","calendarType":"FRIDAY"},
                {"id":7,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Sombra","location":"Paris","description":"Mainer sombra","recurrence":"NONE","calendarType":"FRIDAY"},
                {"id":8,"from":{"day":10,"month":11,"year":2021,"time":{"hour":7,"minute":30}},"to":{"day":12,"month":11,"year":2021,"time":{"hour":10,"minute":30}},"title":"Sombra","location":"Paris","description":"Mainer sombra","recurrence":"NONE","calendarType":"FRIDAY"}


            ],
            // month: date.getMonth()+1,
            // year: date.getYear()
            month: 11,
            year: 2021,

            isPopup: false,
            popupEvent: null

        }
        this.addMonth = this.addMonth.bind(this);
        this.subMonth = this.subMonth.bind(this);
        this.addYear = this.addYear.bind(this);
        this.subYear = this.subYear.bind(this);

        this.hideEventPopup = this.hideEventPopup.bind(this);
        this.showEventPopup = this.showEventPopup.bind(this);

        //TESTING ONLY
        this.deleteEvent = this.deleteEvent.bind(this);
    }

    daysInMonth(month, year) {
        return new Date(year, month, 0).getDate();
    }

    componentDidMount(){
        //console.log(Array.from(Array(this.daysInMonth(11,2021)).keys()));
        //console.log(this.state.month);
    }

    showEventPopup(event){
        this.setState({isPopup: true});
        this.setState({popupEvent: event});
    }

    //TESTING ONLY
    deleteEvent(event){
        this.hideEventPopup();
        let i = this.state.events.indexOf(event);
        this.state.events.splice(i,1);
        this.setState({events: this.state.events.concat([])});
    }

    hideEventPopup(){
        this.setState({isPopup: false});
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
        }
        else{
            this.setState({month: this.state.month+1});
        }
      }

      subMonth(){
        this.hideEventPopup();
        if(this.state.month === 1){
            this.setState({month: 12});
            this.setState({year: this.state.year-1});
        }
        else{
            this.setState({month: this.state.month-1});
        }
      }

      addYear(){
        this.hideEventPopup();
        this.setState({year: this.state.year+1});
      }

      subYear(){
        this.hideEventPopup();
        this.setState({year: this.state.year-1});
      }

    render(){
        return (
            <div align="center">
                <h2>Calendar</h2>
                <h4 className="calendar-month">{this.monthToString[this.state.month] +" " +this.state.year}</h4>
                <button className="calendar-btn" onClick={this.subMonth}>{"<-"}</button>
                <button className="calendar-btn" onClick={this.addMonth}>{"->"}</button>
                <button className="calendar-btn" onClick={this.subYear}>{"<="}</button>
                <button className="calendar-btn" onClick={this.addYear}>{"=>"}</button>
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
                    ? <EventPopup key="popup" event={this.state.popupEvent} hidePopup={this.hideEventPopup} deleteEvent={this.deleteEvent}></EventPopup>
                    : <React.Fragment></React.Fragment>
                }
            </div>
        );
    }
}

export default Calendar;
