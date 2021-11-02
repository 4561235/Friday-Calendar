import React, {Component} from "react";

class CalendarDay extends Component{
    constructor(props, event){
        super(props)
        this.state = {
            event: event
        }
    }
}

export default CalendarDay;