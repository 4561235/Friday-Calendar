import React, {Component} from "react";

class Calendar extends Component{
    constructor(props){
        super(props)
        const date = new Date();
        this.state = {
            events:[],
            month: date.getMonth()+1,
            year: date.getYear()
        }
    }

    daysInMonth(month, year) {
        return new Date(year, month, 0).getDate();
    }

    componentDidMount(){
        //console.log(Array.from(Array(this.daysInMonth(11,2021)).keys()));
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

    render(){
        return (
            <div>
                <h2>Calendar</h2>
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
