import React from 'react';
import { BrowserRouter as Router, Link , Route ,Routes } from 'react-router-dom';  
import './App.css';
import StartMenu from './component/StartMenu';
import Uploadfile from './component/Uploadfile';


function App() {

  return (
    
    <div>
      
        <Routes>
          <Route path="/" element={<StartMenu/>} />
          <Route path="/uploadfile" element={<Uploadfile/>} />
        </Routes>

      
    </div>
    
  );
}

export default App;