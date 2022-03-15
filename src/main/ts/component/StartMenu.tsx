import React from "react";
import './CSSstyle/start.css';
import play_btn from './CSSstyle/image/play.png';
import howtoplay_btn from './CSSstyle/image/howto.png';
import { useNavigate } from "react-router-dom";


const StartMenu = () => {
    
    const navigate = useNavigate();

    const newGame =()=>{
        fetch(`/newgame`)
        navigate("/uploadfile/main")
        
    }

   
    

    return(
    
    <div className ="bg-body">
       
       <div  className ="bg-main" >

       <div className ="btn-float-right-bottom flex flex-col space-y-4 ">
           <button className="btnscale btn-scale"><img src={play_btn} alt="start_button" style={{width:"280px" ,height:"120px"}}
           onClick = {() =>{newGame()}} /></button>
            <button className="btnscale btn-scale"><img src={howtoplay_btn} alt="howto_button" style={{width:"280px" ,height:"120px"}} 
            onClick = {() =>navigate("/howtoplay")} /></button>
       </div>

       </div>

    </div>
   
    );
}   

export default StartMenu