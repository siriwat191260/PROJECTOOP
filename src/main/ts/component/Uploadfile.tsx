import React, { useState } from "react";
import './CSSstyle/upload.css';
import play_btn from './CSSstyle/image/play.png';
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
  const data: (String | ArrayBuffer)[] = ['', '', '', '', '', '']


  const upload = (e, i: number) => {
    e.preventDefault();
    if (data[i] == '') { alert("No Selected File") }
    else {
      fetch(`/uploadfile?file=${data[i]}&number=${i}`)
    }
  }

  const handlefile = (e, i: number) => {
    const file = e.target.files[0];
    const reader = new FileReader();
    reader.readAsText(file);
    reader.onload = () => {
      data[i] = (reader.result)
    }

    /* console.log(reader.result); */
  }

  const GameRun = () => {
    fetch(`/game/run`)
    navigate("/game")
  }


  return (
    <div className="bg-white">
      <div className=" bg-body bg-center bg-no-repeat  w-full h-screen " >
        <div className="px-32 pt-10 mx-52 space-y-10">
          <div className="row space-x-52 ">
            <div className="column space-y-5 shadow-lg border-2 rounded-2xl p-4" style={{ backgroundColor : '#fcadff'}}>
              {/* antibody1 */}
              <img className="ml-28" src={antibody_pic1} alt="antibody1" style={{ width: "120px", height: "120px" }} />
              <div className="flex">
                <input type="file" onChange={e => handlefile(e, 1)} id="file1" name="filename1" />
                <button className="btnscale btn-scale -mt-4" onClick={e => upload(e, 1)} ><img src={submit_btn} alt="submit_button"
                  style={{ width: "160px", height: "50px" }} /></button>
              </div>
            </div>
            {/* virus1 */}
            <div className="column space-y-5 shadow-lg border-2 rounded-2xl p-4" style={{ backgroundColor : '#fcadff'}}>
              <img className="ml-28" src={virus_pic1} alt="virus1" style={{ width: "120px", height: "120px" }} />
              <div className="flex">
                <input type="file" onChange={e => handlefile(e, 4)} id="file4" name="filename1" />
                <button className="btnscale btn-scale -mt-4" onClick={e => upload(e, 4)} ><img src={submit_btn} alt="submit_button" style={{ width: "160px", height: "50px" }}
                /></button>
              </div>
            </div>
          </div>

          {/* antibody2 */}
          <div className="row space-x-52 ">
            <div className="column space-y-5 shadow-lg border-2 rounded-2xl p-4" style={{ backgroundColor : '#fcadff'}}>
              <img className="ml-28" src={antibody_pic2} alt="antibody2" style={{ width: "120px", height: "120px" }} />
              <div className="flex">
                <input type="file" onChange={e => handlefile(e, 2)} id="file2" name="filename1" />
                <button className="btnscale btn-scale -mt-4" onClick={e => upload(e, 2)} ><img src={submit_btn} alt="submit_button" style={{ width: "160px", height: "50px" }}
                /></button></div>
            </div>



            {/* virus2 */}
            <div className="column space-y-5 shadow-lg border-2 rounded-2xl p-4" style={{ backgroundColor : '#fcadff'}}>
              <img className="ml-28" src={virus_pic2} alt="virus2" style={{ width: "120px", height: "120px" }} />
              <div className="flex">
                <input type="file" onChange={e => handlefile(e, 5)} id="file5" name="filename1" />
                <button className="btnscale btn-scale -mt-4" onClick={e => upload(e, 5)} ><img src={submit_btn} alt="submit_button" style={{ width: "160px", height: "50px" }}
                /></button></div>
            </div>
          </div>

          {/* antibody3 */}
          <div className="row space-x-52 ">
            <div className="column space-y-5 shadow-lg border-2 rounded-2xl p-4" style={{ backgroundColor : '#fcadff'}}>
              <img className="ml-28" src={antibody_pic3} alt="antibody3" style={{ width: "120px", height: "120px" }} />
              <div className="flex">
                <input type="file" onChange={e => handlefile(e, 3)} id="file3" name="filename1" />
                <button className="btnscale btn-scale -mt-4" onClick={e => upload(e, 3)} ><img src={submit_btn} alt="submit_button" style={{ width: "160px", height: "50px" }}
                /></button></div>
            </div>



            {/* virus3 */}
            <div className="column space-y-5 shadow-lg border-2 rounded-2xl p-4" style={{ backgroundColor : '#fcadff'}}>
              <img className="ml-28" src={virus_pic3} alt="virus3" style={{ width: "120px", height: "120px" }} />
              <div className="flex">
                <input type="file" onChange={e => handlefile(e, 6)} id="file6" name="filename1" />
                <button className="btnscale btn-scale -mt-4" onClick={e => upload(e, 6)} ><img src={submit_btn} alt="submit_button" style={{ width: "160px", height: "50px" }}
                /></button></div>
            </div>
          </div>

        </div>

      </div >
      <div className="float-right-bottom-btn">
        <button className="btnscale btn-scale "><img src={play_btn} alt="play_button" style={{ width: "325px", height: "130px" }}
          onClick={() => { GameRun() }} /></button>
      </div>

    </div >


  )

}


export default Uploadfile