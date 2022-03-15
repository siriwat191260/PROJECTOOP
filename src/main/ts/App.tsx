import React from 'react';
import { BrowserRouter as Router, Link , Route ,Routes } from 'react-router-dom';  
import './App.css';
import StartMenu from './component/StartMenu';
import Uploadfile from './component/Uploadfile';
import Game from './component/Game';


function App() {

  return (
    
    <div>
      
        <Routes>
          <Route path="/" element={<StartMenu/>} />
          <Route path="/uploadfile/main" element={<Uploadfile/>} />
          <Route path="/game" element={<Game/>}/>
        </Routes>

      
    </div>
    
  );
}

export default App;