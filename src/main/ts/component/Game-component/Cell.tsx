import React from "react";
import { CellStore, selectcell } from "../../stores/Cellstore";
import block from '../CSSstyle/image/block.png';
import anti1 from '../CSSstyle/image/anti1.png'
import anti2 from '../CSSstyle/image/anti2.png'
import anti3 from '../CSSstyle/image/anti3.png'

type celltype = {
    x:number
    y:number
}

let w:number

const render = (host:string) =>{
    if (host === 'anti1')
    return (
      <img src={anti1}></img>
    )
  if (host === 'anti2')
    return (
      <img src={anti2}></img>
    )
  if (host === 'anti3')
    return (
      <img src={anti3}></img>
    )
}

const Cell = ({ x, y }: celltype) => {

    const state = CellStore.useState()
  
    return (
      <td className="w-10 h-10 cursor-pointer p-2 " style={{borderColor: "transparent", backgroundImage: `url(${block})`,backgroundRepeat: 'no-repeat',backgroundPosition: 'center',backgroundSize: 'cover' }} onClick={() => selectcell(y,x,state.host)}>
      {render(state.log[y][x])}
      </td>
    )
  }
  
  export default Cell