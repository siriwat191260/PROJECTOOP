import React from "react";
import { CellStore, selectcell, selectanti } from "../../stores/Cellstore";
import block from '../CSSstyle/image/block.png';
import anti1 from '../CSSstyle/image/anti1.png'
import anti2 from '../CSSstyle/image/anti2.png'
import anti3 from '../CSSstyle/image/anti3.png'
import virus1 from '../CSSstyle/image/virus1.png'
import virus2 from '../CSSstyle/image/virus2.png'
import virus3 from '../CSSstyle/image/virus3.png'

type celltype = {
  x: number
  y: number
}

const render = (host: string) => {
  const state = CellStore.useState();
  if (host == 'anti1')
    return (
      <img src={anti1}></img>
    )
  if (host == 'anti2')
    return (
      <img src={anti2} ></img>
    )
  if (host == 'anti3')
    return (
      <img src={anti3} ></img>
    )
    if (host == 'virus1')
    return (
      <img src={virus1}></img>
    )
  if (host == 'virus2')
    return (
      <img src={virus2}></img>
    )
  if (host == 'virus3')
    return (
      <img src={virus3}></img>
    )
  
}

const fetch_order_location = (a: number, b: number, type: String) => {
  fetch(`/game/addAntibody?x=${a}&y=${b}&type=${type}`)
}

const size = () => {
  const state = CellStore.useState()
  if (state.low < 5 && state.colum < 12) return "w-32 h-32 p-4"
  else if (state.low < 7 && state.colum < 16) return "w-24 h-24 p-4"
  else if (state.low < 8 && state.colum < 19) return "w-20 h-20 p-3.5"
  else if (state.low < 12 && state.colum < 27) return "w-14 h-14 p-2.5"
  else if (state.low < 15 && state.colum < 36) return "w-11 h-11 p-2"
  else if (state.low < 18 && state.colum < 45) return "w-9 h-9 p-1.5"
  else if (state.low < 26 && state.colum < 64) return "w-6 h-6 p-1"
  else if (state.low < 38 && state.colum < 98) return "w-4 h-4 p-0.5"
  else return "w-2 h-2 p-0.5"
}

const Cell = ({ x, y }: celltype) => {

  const state = CellStore.useState()


  return (
    <td className={`${size()} cursor-pointer`} style={{ borderColor: "transparent", backgroundImage: `url(${block})`, backgroundRepeat: 'no-repeat', backgroundPosition: 'center', backgroundSize: 'cover' }} onClick={() => {
      if (state.log[y][x] == '') {
        selectcell(y, x, state.host)
        fetch_order_location(y, x, state.host)
        selectanti('')
      }
    }}>
      {render(state.log[y][x])}
    </td>
  )
}

export default Cell