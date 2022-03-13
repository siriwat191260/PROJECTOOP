import React from "react";
import './CSSstyle/upload.css';
import submit_btn from './CSSstyle/image/submit.png';
import antibody_pic1 from './CSSstyle/image/anti1.png';
import antibody_pic2 from './CSSstyle/image/anti2.png';
import antibody_pic3 from './CSSstyle/image/anti3.png';
import virus_pic1 from './CSSstyle/image/virus1.png';
import virus_pic2 from './CSSstyle/image/virus2.png';
import virus_pic3 from './CSSstyle/image/virus3.png';
import { useNavigate } from "react-router-dom";


const Uploadfile = () => {

    const navigate = useNavigate();

    const upload = () => {
        var file = document.getElementById("antibody1")
        console.log(file)
        fetch(`/uploadfile?f1=${file}`)
        /* navigate("/game") */
    } 

    return(
        <div className="bg-white">
            <div className=" bg-body bg-center bg-no-repeat  w-full h-screen  ;" >
            
            <div className="flex-gap float-right-top ">
              <div className="mb-4 space-y-4"> 
                  <img src={antibody_pic1}  alt="antibody1" style={{width:"200px" ,height:"150px"}}/>
                  <form action="/action_page.php">
                      
                      <input type="file" id="antibody1" name="filename"/>
                      
                    </form>
              </div> 

              <div className="mb-4 space-y-4">
                <img src={antibody_pic2} alt="antibody2" style={{width:"200px" ,height:"150px"}}/>
                <form action="/action_page.php">
                  
                      <input type="file" id="antibody2" name="filename"/>
                  
                </form>
              </div>

              <div className="mb-4 space-y-4"> 
                <img src={antibody_pic3} alt="antibody3" style={{width:"200px" ,height:"150px"}}/>
                <form action="/action_page.php">
                 
                      <input type="file" id="antibody3" name="filename"/>
               
                  
                </form>
             </div>


             
            </div>

            <div className="flex-gap float-right-bottom ">
              <div className="mb-4 space-y-4"> 
                  <img src={virus_pic1}  alt="virus1" style={{width:"200px" ,height:"150px"}}/>
                  <form action="/action_page.php">
                      
                      <input type="file" id="virus1" name="filename"/>
                      
                    </form>
              </div> 

              <div className="mb-4 space-y-4">
                <img src={virus_pic1} alt="virus2" style={{width:"200px" ,height:"150px"}}/>
                <form action="/action_page.php">
                  
                      <input type="file" id="virus2" name="filename"/>
                  
                </form>
              </div>

              <div className="mb-4 space-y-4"> 
                <img src={virus_pic1} alt="virus3" style={{width:"200px" ,height:"150px"}}/>
                <form action="/action_page.php">
                 
                      <input type="file" id="virus3" style={{width:"200px" ,height:"150px"}}/>
               
                  
                </form>
              </div>


             
            </div>
            <div className="float-right-bottom-btn">
            <button className="btn-scale "><img src={submit_btn} alt="submit_button" style={{width:"200px" ,height:"80px"}} 
            onClick = {() =>{upload}} /></button>
            </div>
            
          </div>

        </div>
    )

}


export default Uploadfile