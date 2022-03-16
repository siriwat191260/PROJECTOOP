import React from "react";
import Body from "./Game-component/body";
import { receiveData } from "../api/GameApi";
import gameloop from "./Game-component/gameloop";

const Game = () => {


  return (
    <div className=" bg-body bg-center bg-no-repeat  w-full h-screen" style={{ backgroundColor: "#FFD7B5" }}>
      <div className="my-auto space-y-3 ">
        {gameloop()}
        {receiveData()}
        <Body/>
      </div>
    </div >
  );
}

export default Game;