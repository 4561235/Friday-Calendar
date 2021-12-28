import React, {Component} from "react";
import "./SubwayStatus.css";

class SubwayStatus extends Component{

    constructor(props){
        super(props);
        this.state = {
            statuses: []
        }
    }

    componentDidMount(){
        this.fetchMetroStatus();
    }

    fetchMetroStatus(){
        fetch("https://api-ratp.pierre-grimaud.fr/v4/traffic" , {
            method: 'GET', // *GET, POST, PUT, DELETE, etc.
            })
            .then(response => response.json())
            .then( (user) => {
                var jsonString = JSON.stringify(user);
                var temp = JSON.parse(jsonString);
                this.renderMetroStatus(temp);
            })
            .catch(err => {
                console.log(err);
            });
          
    }

    renderMetroStatus(statusJson){
        let renderFinal = [];
        let metrosTab = [];

        let metros = statusJson.result.metros;
        metros.forEach(m =>{
            metrosTab.push(<td key={m.line} className="subway-line">
                <p><b>{m.line}</b> {m.title}</p>
            </td>);
        });

        let rers = statusJson.result.rers;
        rers.forEach(m =>{
            metrosTab.push(<td key={m.line} className="subway-line">
                <p><b>{m.line}</b> {m.title}</p>
            </td>);
        });

        let tramways = statusJson.result.tramways;
        tramways.forEach(m =>{
            metrosTab.push(<td key={m.line} className="subway-line">
                <p><b>{m.line}</b> {m.title}</p>
            </td>);
        });

        let pushTab = []
        for(let i = 0; i < metrosTab.length; i++){
            pushTab.push(metrosTab[i]);

            if(pushTab.length === 9){
                renderFinal.push(<tr key={i}>{pushTab}</tr>)
                pushTab = [];
            }
        }
        renderFinal.push(<tr key={"last"}>{pushTab}</tr>)
        


       this.setState({statuses: renderFinal});
    }


    render(){
        return(
            <div align="center">
                <b>Status des metros, rers, tramways parisiens:</b>
                <br></br>
                <br></br>
                <table>
                    <tbody>
                        {this.state.statuses}
                    </tbody>
                </table>
                <br></br>
                <br></br>
            </div>
        );
    }
}

export default SubwayStatus;