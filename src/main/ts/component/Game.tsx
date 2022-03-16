import React from "react";
import Body from "./Game-component/body";
import { receiveData } from "../api/GameApi";
import { useState, useEffect } from 'react';

const Rerender = () =>{
    useEffect(() => {
      setInterval(() => {
         return(<Body/>) 
      }, (1000))
  }, [])
  }
const Game = () => {
  
  
  return (
    <div className=" bg-body bg-center bg-no-repeat  w-full h-screen" style={{ backgroundColor: "#FFD7B5" }}>
      <div className="my-auto space-y-3 ">
      {receiveData()}
        {Rerender()}
      </div>
    </div >
  );
}

export default Game;