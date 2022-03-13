import React from "react";
import './CSSstyle/start.css';
import play_btn from './CSSstyle/image/play.png';
import howtoplay_btn from './CSSstyle/image/howto.png';


const StartMenu = () => {

    const openTab = (url: string | URL) =>{
        window.location.replace(url);
    }

    return(
    
    <div className ="bg-green">
       
       <div  className ="bg-main" >

       <div className ="btn-float-right-bottom flex flex-col space-y-4 ">
           <button className="btn-scale"><img src={play_btn} alt="start_button" style={{width:"280px" ,height:"120px"}}
           onClick = {() =>openTab('http://localhost:8080/uploadfile')} /></button>
            <button className="btn-scale"><img src={howtoplay_btn} alt="howto_button" style={{width:"280px" ,height:"120px"}} 
            onClick = {() =>openTab('http://localhost:8080/howtoplay')} /></button>
       </div>

       </div>

    </div>
   
    );
}

export default StartMenu