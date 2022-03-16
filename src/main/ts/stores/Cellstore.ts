import { Store } from "pullstate";
import React from "react";
import setsize from '../component/Game-component/Cell';

type Cell = {
    log: string[][]
    host: string
    low:number
    colum:number
}

let m:number
let n:number

m=10
n=10
const createcell = () =>{
    
    const output : string[][] = []
    for (let i=0; i<m; i++){
        output[i] = []
        for(let j=0; j<n; j++){
            output[i].push('')
        }
    }
    return output
}

export const CellStore = new Store<Cell>({
    log: createcell(),
    host: "",
    low:m,
    colum:n
})

export const selectanti = (anti: string) => {
    CellStore.update(s => {
        s.host = anti
    })
}

export const selectcell = (x: number, y:number,host:string) => {
    CellStore.update(s => {
        s.log[x][y] = host
    })
}