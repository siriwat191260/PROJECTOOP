import React from "react";
import { CellStore } from "../../stores/Cellstore";
import Cell from "./Cell";

const Body = () => {
    const state = CellStore.useState()

    return (
        <table className="ring-2 ring-gray-200 rounded-md mx-auto">
          <tbody>
            {state.log.map((row, i) =>
              <tr key={i}>
                {row.map((cell, j) =>
                  <Cell x={j} y={i} key={`${j}${i}`} />)}
              </tr>
            )}
          </tbody>
        </table>
      )
}

export default Body