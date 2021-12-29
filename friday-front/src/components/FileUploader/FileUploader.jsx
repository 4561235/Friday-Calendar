import React, {Component} from "react";
import "./fileUploader.css";

class FileUploader extends Component{

    constructor(props){
        super(props);
        this.state = {
            file: null,
            isLoaded: false,
            uploadMessage: "",

            icalUrl:""
        }

        this.fileUploadHandler = this.fileUploadHandler.bind(this);
        this.fetchIcalFromUrl = this.fetchIcalFromUrl.bind(this);
    }

    fileUploadHandler(){
        console.log("Uploaded");
        console.log(this.state.file);
        
        var reader = new FileReader();
        
        reader.onload = (e) => this.sendIcal("http://localhost:8080/calendar-events/sendIcal/" +encodeURIComponent(e.target.result));
        reader.readAsText(this.state.file);
        
    }

    sendIcal(url){
        var promise = fetch(url , {
          method: 'GET', // *GET, POST, PUT, DELETE, etc.
          mode: 'cors', // no-cors, *cors, same-origin
          cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
          credentials: 'same-origin', // include, *same-origin, omit
          headers: {
              'Content-Type': 'application/json'//,
              //'Authorization': `Bearer ${this.$jwt.getToken()}`
              // 'Content-Type': 'application/x-www-form-urlencoded',
          },
          redirect: 'follow', // manual, *follow, error
          referrerPolicy: 'no-referrer'
          })
          .then(() => this.setState({uploadMessage: "Envoi reussi"}))
          .then(() => this.props.refreshEvents())
          .catch(err => {
            this.setState({uploadMessage: "Erreur d'envoi"})
        });

        return promise;
    }

    fetchIcalFromUrl(){
        console.log(this.state.icalUrl);
        fetch(this.state.icalUrl , {
            method: 'GET', // *GET, POST, PUT, DELETE, etc.
            mode: 'cors', // no-cors, *cors, same-origin
            cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
            credentials: 'same-origin', // include, *same-origin, omit
            headers: {
                'Content-Type': 'application/json'//,
                //'Authorization': `Bearer ${this.$jwt.getToken()}`
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: 'follow', // manual, *follow, error
            referrerPolicy: 'no-referrer'
            })
            .then(f => {
                this.setState({file: f});
                this.fileUploadHandler();
            })
            .catch(err => {
              this.setState({uploadMessage: "Erreur d'envoi"})
          });
    }

    render(){
        return(
            <div align="center">
                <br></br>
                <input type="file" name="file" onChange={e =>{ this.setState({file: e.target.files[0], isLoaded: true, uploadMessage:""}) }}/>
                <button className="btn" type="button" onClick={this.fileUploadHandler}>Uploader le fichier</button>
                {this.state.uploadMessage}
                <br></br>
                <input type="text" className="btn" value={this.state.icalUrl} onChange={e =>{ this.setState({icalUrl: e.target.value}) }}/>
                <button className="btn" type="button" onClick={this.fetchIcalFromUrl}>Uploader a partir d'une URL</button>

                {/* <input className="text-input" type="time" min={this.state.fromTime} value={this.state.toTime} onChange={e => this.setState({toTime: e.target.value})}/> */}
            </div>
        );
    }
}

export default FileUploader;