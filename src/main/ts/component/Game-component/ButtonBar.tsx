import React from 'react'
import zoomin from '../CSSstyle/image/zoomin.png'
import zoomout from '../CSSstyle/image/zoomout.png'
import speedup from '../CSSstyle/image/speed.png'
import pause from '../CSSstyle/image/pause.png'

const Bar = () => {
  return (
    <div className="flex justify-center p-6 space-x-6 ">
      <div className='pt-7 border-white rounded-full border-4 flex justify-center' style={{ width: "450px", height: "100px", backgroundColor: '#a6d2ff' }}>
        <text className='text-4xl'>Antibody credit : 1000 </text> 
      </div>
      <img src={zoomin}  className="" style={{ width: "100px"}}></img>
      <img src={zoomout}  className="" style={{ width: "100px"}}></img>
      <img src={speedup} className="" style={{ width: "100px"}}></img>
      <text className='pt-7 text-4xl '>time left: 10 </text>
      <img src={pause} style={{ width: "100px"}}></img>
    </div>
  )
}

export default Bar