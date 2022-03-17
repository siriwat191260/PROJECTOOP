import React, { Component } from "react";
import { TransformWrapper, TransformComponent } from "react-zoom-pan-pinch";
import { CellStore } from "../../stores/Cellstore";
import { ApiDataStore, settimeapi, endgame } from "../../api/GameApi";
import Cell from "./Cell";
import zoomin from '../CSSstyle/image/zoomin.png'
import zoomout from '../CSSstyle/image/zoomout.png'
import speedup from '../CSSstyle/image/speedup.png'
import speeddown from '../CSSstyle/image/speeddown.png'
import pause from '../CSSstyle/image/pause.png'
import playbt from '../CSSstyle/image/playbt.png'
import Antipicker from "./antipicker";
import { bottonpause } from "../../api/GameApi";
import { buttonspeedUp , buttonspeedDown } from "../../api/GameApi";
import '../CSSstyle/btn.css';

let pauseCheck: boolean = false
let speedCheck: boolean = false
let img = pause

const Toggle = () =>{
  if(pauseCheck == false){
    img = playbt
    pauseCheck = true
  }else{
    img = pause
    pauseCheck = false
  }
}

const Body = () => {
  const state = CellStore.useState()
  const api = ApiDataStore.useState()

  const check =() =>{
    let end = endgame()
    if(end == 1){
      return <text className='pt-2 text-4xl pl-10'>Game over   virus win! </text>

    }else if(end == 2){
      return <text className='pt-2 text-4xl pl-10'>Game over   antibody win!</text>
    }
  }


    return (
    <div>
      <TransformWrapper initialScale={1} initialPositionX={0} initialPositionY={0}>
        {({ zoomIn, zoomOut, ...rest }) => (
          <React.Fragment>
            {/* bar */}
            <div className="tools">
              <div className="flex justify-center p-6 space-x-6 ">
                <div className='pt-6 border-white rounded-full border-8 flex justify-center' style={{ width: "450px", height: "100px", backgroundColor: '#a6d2ff' }}>
                  <text className='text-4xl'>Antibody credit : {api.antiCredit} </text>
                </div>
                <img src={zoomin} onClick={() => zoomIn()} className="btn-scale" style={{ width: "100px" }}></img>
                <img src={zoomout} onClick={() => zoomOut()} className="btn-scale " style={{ width: "100px" }}></img>
                <img src={speeddown} onClick={()=>{buttonspeedDown()}} className="btn-scale" style={{ width: "100px" }}></img>
                <img src={speedup} onClick={()=>{buttonspeedUp()}} className="btn-scale" style={{ width: "100px" }}></img>
                <text className='pt-7 text-4xl '>time left: {settimeapi} </text>
                <img src={img} className="btn-scale" 
                onClick={() => 
                  {Toggle()
                  bottonpause()}} style={{ width: "100px" }}></img>
              </div>
            </div>

            <div className="grid grid-rows-2 grid-flow-col gap-8 justify-center mt-4 ">
              {/* body */}
              <div className="row-end-3 row-span-2">
                <table className="rounded-lg mx-auto shadow-xl" style={{ backgroundColor: "#F3A883" }}>
                  <TransformComponent>
                    <tbody>
                      {state.log.map((row, i) =>
                        <tr key={i}>
                          {row.map((cell, j) =>
                            <Cell x={j} y={i} key={`${j}${i}`} />)}
                        </tr>
                      )}
                    </tbody>
                  </TransformComponent>
                </table >
              </div>
              {/* antipicker */}
              <div className="row-start-1 row-end-4">
                <Antipicker />
              </div>
            </div>
          </React.Fragment>
        )}
      </TransformWrapper>
      {check()}
    </div>
  )
}

export default Body