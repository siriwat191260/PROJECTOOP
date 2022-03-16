import React, { Component } from "react";
import { TransformWrapper, TransformComponent } from "react-zoom-pan-pinch";
import { CellStore } from "../../stores/Cellstore";
import { ApiDataStore } from "../../api/GameApi";
import Cell from "./Cell";
import zoomin from '../CSSstyle/image/zoomin.png'
import zoomout from '../CSSstyle/image/zoomout.png'
import speedup from '../CSSstyle/image/speed.png'
import pause from '../CSSstyle/image/pause.png'
import playbt from '../CSSstyle/image/playbt.png'
import Antipicker from "./antipicker";
import { bottonpause } from "../../api/GameApi";


let pauseCheck: boolean = false
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


    return (
    <div>
      <TransformWrapper initialScale={1} initialPositionX={200} initialPositionY={100}>
        {({ zoomIn, zoomOut, ...rest }) => (
          <React.Fragment>
            {/* bar */}
            <div className="tools">
              <div className="flex justify-center p-6 space-x-6 ">
                <div className='pt-6 border-white rounded-full border-8 flex justify-center' style={{ width: "450px", height: "100px", backgroundColor: '#a6d2ff' }}>
                  <text className='text-4xl'>Antibody credit : {api.antiCredit} </text>
                </div>
                <img src={zoomin} onClick={() => zoomIn()} className="cursor-pointer" style={{ width: "100px" }}></img>
                <img src={zoomout} onClick={() => zoomOut()} className="cursor-pointer " style={{ width: "100px" }}></img>
                <img src={speedup} className="cursor-pointer" style={{ width: "100px" }}></img>
                <text className='pt-7 text-4xl '>time left: 10 </text>
                <img src={img} className="cursor-pointer" 
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
    </div>
  )
}

export default Body