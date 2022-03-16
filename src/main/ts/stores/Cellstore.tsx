import { Store } from "pullstate";
import React from "react";
type Cell = {
    log: string[][]
    host: string
    low:number
    colum:number
}

let m:number
let n:number

m=5
n=5
const createcell = () =>{
    
    const output : string[][] = []
    for (let i=1; i<=m; i++){
        output[i] = []
        for(let j=1; j<=n; j++){
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

export const renderApi = (type: number, x:number, y:number) =>{
    if(type == 1){
        CellStore.update(s =>{
            s.log[x][y] = "anti1"
        })
    }else if(type == 2){
        CellStore.update(s =>{
            s.log[x][y] = "anti2"
        })
    }else if(type == 3){
        CellStore.update(s =>{
            s.log[x][y] = "anti3"
        })
    }
    else if(type == 4){
        CellStore.update(s =>{
            s.log[x][y] = "virus1"
        })
    }else if(type == 5){
        CellStore.update(s =>{
            s.log[x][y] = "virus2"
        })
    }else if(type == 6){
        CellStore.update(s =>{
            s.log[x][y] = "virus3"
        })
    }
}