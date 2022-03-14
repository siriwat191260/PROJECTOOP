import React, { useState } from "react";
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
    const data:(String|ArrayBuffer)[] = ['','','','','','']


    const upload = (e,i:number) =>{
      e.preventDefault();
      if(data[i] == '') 
      {alert("No Selected File")}
      else{
      fetch(`/uploadfile?file=${data[i]}&number=${i}`)
      }
    }

    const handlefile =(e,i:number)=>{
        const file = e.target.files[0];
        const reader = new FileReader();
        reader.readAsText(file);
        reader.onload = () =>{
          data[i] = (reader.result)
        }
        
        console.log(reader.result);
    }

  
    return(
        <div className="bg-white">
            <div className=" bg-body bg-center bg-no-repeat  w-full h-screen  ;" >
            <div className="flex-gap float-right-top ">
              <div className="mb-4 space-y-4"> 
                  <img src={antibody_pic1}  alt="antibody1" style={{width:"200px" ,height:"150px"}}/>
                  {/* <form onSubmit={upload}> */}
                      
                     <input type="file" onChange={e => handlefile(e,1)} id="file1" name="filename1"/>
                      <button className="btn-scale" onClick={e => upload(e,1)} ><img src={submit_btn} alt="submit_button" style={{width:"100px" ,height:"40px"}} 
              /></button>

                    {/* </form>  */}
                     
                    

              </div> 

              <div className="mb-4 space-y-4">
                <img src={antibody_pic2} alt="antibody2" style={{width:"200px" ,height:"150px"}}/>
                <input type="file" onChange={e => handlefile(e,2)} id="file2" name="filename1"/>
                      <button className="btn-scale" onClick={e => upload(e,2)} ><img src={submit_btn} alt="submit_button" style={{width:"100px" ,height:"40px"}} 
              /></button>
              </div>

              <div className="mb-4 space-y-4"> 
                <img src={antibody_pic3} alt="antibody3" style={{width:"200px" ,height:"150px"}}/>
                <input type="file" onChange={e => handlefile(e,3)} id="file3" name="filename1"/>
                      <button className="btn-scale" onClick={e => upload(e,3)} ><img src={submit_btn} alt="submit_button" style={{width:"100px" ,height:"40px"}} 
              /></button>
             </div>


             
            </div>

            <div className="flex-gap float-right-bottom ">
              <div className="mb-4 space-y-4"> 
                  <img src={virus_pic1}  alt="virus1" style={{width:"200px" ,height:"150px"}}/>
                  <input type="file" onChange={e => handlefile(e,4)} id="file4" name="filename1"/>
                      <button className="btn-scale" onClick={e => upload(e,4)} ><img src={submit_btn} alt="submit_button" style={{width:"100px" ,height:"40px"}} 
              /></button>
              </div> 

              <div className="mb-4 space-y-4">
                <img src={virus_pic2} alt="virus2" style={{width:"200px" ,height:"150px"}}/>
                <input type="file" onChange={e => handlefile(e,5)} id="file5" name="filename1"/>
                      <button className="btn-scale" onClick={e => upload(e,5)} ><img src={submit_btn} alt="submit_button" style={{width:"100px" ,height:"40px"}} 
              /></button>
              </div>

              <div className="mb-4 space-y-4"> 
                <img src={virus_pic3} alt="virus3" style={{width:"200px" ,height:"150px"}}/>
                <input type="file" onChange={e => handlefile(e,6)} id="file6" name="filename1"/>
                      <button className="btn-scale" onClick={e => upload(e,6)} ><img src={submit_btn} alt="submit_button" style={{width:"100px" ,height:"40px"}} 
              /></button>
              </div>


             
            </div>
           {/*  <div className="float-right-bottom-btn">
              <button className="btn-scale "><img src={submit_btn} alt="submit_button" style={{width:"200px" ,height:"80px"}} 
              onClick = {() =>{upload}} /></button>
            </div> */}
            
          </div>

        </div>
    )

}


export default Uploadfile