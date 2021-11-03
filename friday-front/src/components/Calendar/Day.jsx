import React, {Component} from "react";

class Day extends Component{
    constructor(props){
        super(props)
        this.state = {
            event: null,
            day: props.day
        }
    }

    componentDidMount(){
        //console.log("Day " +this.state.day);
    }

    render(){
        return(
            <React.Fragment>
                {this.state.day}
                {" Manger pleins de trucs"}
            </React.Fragment>
        );
    }
}

export default Day;