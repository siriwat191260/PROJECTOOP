import React from "react";
import { CellStore, selectanti } from "../../stores/Cellstore";
import { ApiDataStore } from "../../api/GameApi";
import anti1 from '../CSSstyle/image/anti1.png'
import anti2 from '../CSSstyle/image/anti2.png'
import anti3 from '../CSSstyle/image/anti3.png'

type antibody = {
    anti: string
}

const render = (anti: string) => {
    if (anti === 'anti1')
        return (
            <img src={anti1}></img>
        )
    if (anti === 'anti2')
        return (
            <img src={anti2}></img>
        )
    if (anti === 'anti3')
        return (
            <img src={anti3}></img>
        )
}

const Selectantibutton = ({ anti }: antibody) => {
    const state = CellStore.useState()

    const renderselect = () => {
        if (state.host == anti) {
            return "ring-8 ring-red-400"
        } else return ""
    }

    return (
        <div className={`${renderselect()} rounded-md w-24 h-24 cursor-pointer justify-self-center`}
            onClick={() => { selectanti(anti) }}
        >
            {render(anti)}
        </div>
    )
}

const Antipicker = () => {
    const api = ApiDataStore.useState()
    
    return (
        <div className="grid grid-flow-row justify-center pt-4 border-white border-8 rounded-lg shadow-lg" style={{ width: "150px", height: "450px", backgroundColor: "#e9b4f1" }}>
            <text className="text-2xl font-bolds text-center ">Buy antibody cost : {api.antiCreditCost}</text>
            <Selectantibutton anti='anti1' />
            <Selectantibutton anti='anti2' />
            <Selectantibutton anti='anti3' />
        </div>
    )
}

export default Antipicker