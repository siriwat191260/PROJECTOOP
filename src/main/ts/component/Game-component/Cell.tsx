import React from "react";
import { CellStore, selectcell } from "../../stores/Cellstore";
import block from '../CSSstyle/image/block.png';
import anti1 from '../CSSstyle/image/anti1.png'
import anti2 from '../CSSstyle/image/anti2.png'
import anti3 from '../CSSstyle/image/anti3.png'

type celltype = {
  x: number
  y: number
}

let order: number = 0

const render = (host: string) => {
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

const fetch_order_location = (a: number, b: number,  type:String , order: number ) => {
  
  fetch(`/game/orderlocation?x=${a}&y=${b}&type=${type}&order=${order}`)
}

const size = () => {
  const state = CellStore.useState()
  if (state.low < 6 && state.colum < 12) return "w-32 h-32 p-4"
  else return "w-24 h-24 p-4"
}

const Cell = ({ x, y }: celltype) => {

  const state = CellStore.useState()

  
  return (
    <td className={`${size()} cursor-pointer`} style={{ borderColor: "transparent", backgroundImage: `url(${block})`, backgroundRepeat: 'no-repeat', backgroundPosition: 'center', backgroundSize: 'cover' }} onClick={() => {
      order = order + 1
      selectcell(y, x, state.host)
      fetch_order_location(y, x, state.host , order )
    }}>
      {render(state.log[y][x])}
    </td>
  )
}

export default Cell