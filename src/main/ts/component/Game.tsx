import React from "react";
import Body from "./Game-component/body";
import Antipicker from "./Game-component/antipicker";
import Bar from "./Game-component/ButtonBar";
const Game = () => {

  return (
    <div style={{ backgroundColor: "#FFD7B5" }}>
      <div className="my-auto space-y-3 ">
        <Bar />
        <div className="flex ">
          <Body />
          <Antipicker />
        </div>
      </div>
    </div >
  );
}

export default Game;