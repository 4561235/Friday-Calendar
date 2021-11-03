import React, {Component} from "react";

class Day extends Component{
    constructor(props){
        super(props)
    }

    componentDidMount(){
        //console.log("Day " +this.state.day);
        //console.log(this.state.events);
    }

    componentDidUpdate() {
  
    }


    render(){
        return(
            <React.Fragment>
                {this.props.day}
                <br></br>
                {this.props.events.map(ev => <React.Fragment key={ev.id}><br></br>{ev.description}</React.Fragment>)}
                <br></br>
            </React.Fragment>
        );
    }
}

export default Day;