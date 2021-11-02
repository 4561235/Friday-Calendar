import React, {Component} from "react";

class Calendar extends Component{

    monthToString = {1:"Janvier", 2:"Fevrier", 3:"Mars", 4:"Avril", 5:"Mai", 6:"Juin", 7:"Juillet", 8:"Aout", 9:"Septembre", 10:"Octobre", 11:"Novembre", 12:"Decembre"}

    constructor(props){
        super(props)
        const date = new Date();
        this.state = {
            events: [],
            // month: date.getMonth()+1,
            // year: date.getYear()
            month: 11,
            year: 2021
        }
        this.addMonth = this.addMonth.bind(this);
        this.subMonth = this.subMonth.bind(this);
        this.addYear = this.addYear.bind(this);
        this.subYear = this.subYear.bind(this);
    }

    daysInMonth(month, year) {
        return new Date(year, month, 0).getDate();
    }

    componentDidMount(){
        //console.log(Array.from(Array(this.daysInMonth(11,2021)).keys()));
        console.log(this.state.month);
    }

    renderCalendar = () => {
        let tab = Array.from(Array(this.daysInMonth(this.state.month, this.state.year)).keys());
        let retTab = [];

        let row = 1;
        let accTab = [];
        for (let index = 0; index < tab.length; index++) {
            if (index === row*7) {
                //Push a row
                retTab.push(<tr key={index}>{accTab}</tr>);
                accTab=[];
                row+=1;
            }
            //Push the day
            accTab.push(<td key={index}>{tab[index]+1}</td>);
        }
        //Push remaining row
        retTab.push(<tr key={5}>{accTab}</tr>);

        return retTab;
      }

      addMonth(){
        if(this.state.month === 12){
            this.setState({month: 1});
            this.setState({year: this.state.year+1});
        }
        else{
            this.setState({month: this.state.month+1});
        }
      }

      subMonth(){
        if(this.state.month === 1){
            this.setState({month: 12});
            this.setState({year: this.state.year-1});
        }
        else{
            this.setState({month: this.state.month-1});
        }
      }

      addYear(){
        this.setState({year: this.state.year+1}); 
      }

      subYear(){
        this.setState({year: this.state.year-1}); 
      }

    render(){
        return (
            <div align="center">
                <h2>Calendar</h2>
                <h4>{this.monthToString[this.state.month] +" " +this.state.year}</h4>
                <button onClick={this.subMonth}>{"<-"}</button>
                <button onClick={this.addMonth}>{"->"}</button>
                <button onClick={this.subYear}>{"<="}</button>
                <button onClick={this.addYear}>{"=>"}</button>
                <table>
                    <tbody>
                        {this.renderCalendar()}
                    </tbody>
                </table>
                
            </div>
        );
    }
}

export default Calendar;
