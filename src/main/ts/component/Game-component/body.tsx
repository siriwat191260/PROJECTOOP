import React, { Component } from "react";
import { TransformWrapper, TransformComponent } from "react-zoom-pan-pinch";
import { CellStore } from "../../stores/Cellstore";
import Cell from "./Cell";
import zoomin from '../CSSstyle/image/zoomin.png'
import zoomout from '../CSSstyle/image/zoomout.png'

const Body = () => {
  const state = CellStore.useState()

  return (
    <table className="rounded-lg mx-auto" style={{ backgroundColor: "#F3A883", width:'70%' }}>
      <TransformWrapper
        initialScale={1}
        initialPositionX={200}
        initialPositionY={100}
      >
        {({ zoomIn, zoomOut, resetTransform, ...rest }) => (
          <React.Fragment>
            <div className="tools">
              <img src={zoomin} onClick={() => zoomIn()} className="" style={{ width: "100px" }}></img>
              <img src={zoomout} onClick={() => zoomOut()} className="" style={{ width: "100px" }}></img>
              <button onClick={() => resetTransform()}>x</button>
            </div>
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
          </React.Fragment>
        )}
      </TransformWrapper>
    </table>
  )
}

export default Body