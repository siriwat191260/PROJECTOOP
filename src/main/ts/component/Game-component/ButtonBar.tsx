import React from 'react'
import zoomin from '../CSSstyle/image/zoomin.png'
import zoomout from '../CSSstyle/image/zoomout.png'
import speedup from '../CSSstyle/image/speed.png'
import pause from '../CSSstyle/image/pause.png'

const Bar = () => {
  return (
    <div className="flex p-6 space-x-6 mx-auto max-w-screen-md">
      <div className='pt-2 border-white rounded-full border-4' style={{ width: "250px", height: "50px", backgroundColor: '#a6d2ff' }}>
        <text className='flex justify-center'>Antibody credit : 1000 </text> 
      </div>
      <img src={zoomin} className="" style={{ width: "60px", height: "60px" }}></img>
      <img src={zoomout} className="" style={{ width: "60px", height: "60px" }}></img>
      <img src={speedup} className="" style={{ width: "60px", height: "60px" }}></img>
      <text className='pt-4'>time left: </text>
      <img src={pause} style={{ width: "60px", height: "60px" }}></img>
    </div>
  )
}

export default Bar