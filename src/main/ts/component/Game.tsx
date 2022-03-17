import React from "react";
import Body from "./Game-component/body";
import { receiveData } from "../api/GameApi";
import gamebg from "../component/CSSstyle/image/gamebg.jpg"

const Game = () => {


  return (
    <div className="bg-center bg-no-repeat w-full h-screen" style={{ backgroundImage: `url(${gamebg})` }}>
      <div className="my-auto space-y-3 ">
        
        {receiveData()}
        <Body/>
      </div>
    </div >
  );
}

export default Game