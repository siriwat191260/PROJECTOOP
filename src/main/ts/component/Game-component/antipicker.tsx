import React from "react";
import { CellStore,selectanti } from "../../stores/Cellstore";
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
        <div className={`${renderselect()} rounded-md w-12 h-12 cursor-pointer`}
            onClick={() => { selectanti(anti) }}
        >
            {render(anti)}
        </div>
    )
}

const Antipicker = () => {
    return (
        <div className="rounded space-y-10 p-6 mr-4" style={{ width: "100px", height: "300px", backgroundColor: "#b6e9ff" }}>
            <Selectantibutton anti='anti1' />
            <Selectantibutton anti='anti2' />
            <Selectantibutton anti='anti3' />
        </div>
    )
}

export default Antipicker