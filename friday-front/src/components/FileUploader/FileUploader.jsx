import React, {Component} from "react";
import "./fileUploader.css";

class FileUploader extends Component{

    constructor(props){
        super(props);
        this.state = {
            file: null,
            isLoaded: false,
            uploadMessage: ""
        }

        this.fileUploadHandler = this.fileUploadHandler.bind(this);
    }

    fileUploadHandler(){
        // console.log("Uploaded");
        // console.log(this.state.file);
        
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
          .catch(err => {
            this.setState({uploadMessage: "Erreur d'envoi"})
        });

          return promise;
    }

    render(){
        return(
            <div align="center">
                <br></br>
                <input type="file" name="file" onChange={e =>{ this.setState({file: e.target.files[0], isLoaded: true, uploadMessage:""}) }}/>
                <button className="btn" type="button" onClick={this.fileUploadHandler}>Uploader le fichier</button>
                {this.state.uploadMessage}
            </div>
        );
    }
}

export default FileUploader;