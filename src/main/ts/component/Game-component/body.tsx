import React, { Component } from "react";
import { TransformWrapper, TransformComponent } from "react-zoom-pan-pinch";
import { CellStore } from "../../stores/Cellstore";
import Cell from "./Cell";
import zoomin from '../CSSstyle/image/zoomin.png'
import zoomout from '../CSSstyle/image/zoomout.png'
import speedup from '../CSSstyle/image/speed.png'
import pause from '../CSSstyle/image/pause.png'
import Antipicker from "./antipicker";

const Body = () => {
  const state = CellStore.useState()

  return (
    <div>
      <TransformWrapper initialScale={1} initialPositionX={200} initialPositionY={100}>
        {({ zoomIn, zoomOut, resetTransform, ...rest }) => (
          <React.Fragment>
            {/* bar */}
            <div className="tools">
              <div className="flex justify-center p-6 space-x-6 ">
                <div className='pt-7 border-white rounded-full border-4 flex justify-center' style={{ width: "450px", height: "100px", backgroundColor: '#a6d2ff' }}>
                  <text className='text-4xl'>Antibody credit : 1000 </text>
                </div>
                <img src={zoomin} onClick={() => zoomIn()} className="" style={{ width: "100px" }}></img>
                <img src={zoomout} onClick={() => zoomOut()} className="" style={{ width: "100px" }}></img>
                <button onClick={() => resetTransform()}>x</button>
                <img src={speedup} className="" style={{ width: "100px" }}></img>
                <text className='pt-7 text-4xl '>time left: 10 </text>
                <img src={pause} style={{ width: "100px" }}></img>
              </div>
            </div>

            <div className="flex ">
            {/* body */}
              <table className="rounded-lg mx-auto" style={{ backgroundColor: "#F3A883" }}>
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

              {/* antipicker */}
              <Antipicker />
            </div>
          </React.Fragment>
        )}
      </TransformWrapper>
    </div>
  )
}

export default Body