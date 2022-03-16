import axios from 'axios'
import { Store } from "pullstate";
import { useState, useEffect } from 'react';
import { renderApi, updatemn } from '../stores/Cellstore';

const API_URL = 'http://localhost:8080/bodyData'

let pause: boolean = false
let set:boolean = true

type BodyData = {
    m: number
    n: number
    antiCredit: number
    type: number[]
    posX: number[]
    posY: number[]
    order: number[]
    antiCreditCost: number
    antiNum: number
    virusNum: number
    antiHealth: number
    virusHealth: number
}

export const ApiDataStore = new Store<BodyData>({
    m: 0,
    n: 0,
    antiCredit: 0,
    type: [],
    posX: [],
    posY: [],
    order: [],
    antiCreditCost: 0,
    antiNum: 0,
    virusNum: 0,
    antiHealth: 0,
    virusHealth: 0
})

export const bottonpause = () => {
    pause = !pause
    fetch(`/game/pause?p=${pause}`)
}
export const receiveData = () => {
    const [data, setData] = useState<BodyData>();

    const fetchData = async () => {
        try {
            const resp = await axios.get<BodyData>(API_URL)
            if (data != resp.data) {
                setData(resp.data)
                console.log(data)
                if(set) {
                    updatemn(data.m,data.n)
                    set = false
                }
            }
            
        }
        catch (err) {
            console.log(err)
        }
    }

    useEffect(() => {
        setInterval(() => {
            fetchData()
        }, (1000))
    }, [])

    useEffect(() => {
        if (pause != true) {
            if (data != null) {
                ApiDataStore.update(s => {
                    s.m = data.m
                    s.n = data.n
                    s.antiCredit = data.antiCredit
                    s.type = data.type
                    s.posX = data.posX
                    s.posY = data.posY
                    s.order = data.order
                    s.antiCreditCost = data.antiCreditCost
                    s.antiNum = data.antiNum
                    s.virusNum = data.virusNum
                    s.antiHealth = data.antiHealth
                    s.virusHealth = data.virusHealth
                    
                    updatemn(data.m,data.n)

                    data.order.map(s => {
                        renderApi(data.type[s],data.posX[s],data.posY[s]-1)
                    })
            

                    console.log("M :" + data.m)
                    console.log("N :" + data.n)
                    console.log("AntiCredit :" + data.antiCredit)
                    console.log("Type :" + data.type)
                    console.log("PosX :" + data.posX)
                    console.log("PosY :" + data.posY)
                    console.log("Order :" + data.order)
                    console.log("AntiCreditCost :" + data.antiCreditCost)
                    console.log("AntiNum :" + data.antiNum)
                    console.log("VirusNum :" + data.virusNum)
                    console.log("AntiHealth :" + data.antiHealth)
                    console.log("VirusHealth :" + data.virusHealth)
                })
            }
        } else {
            console.log("pause")
        }
    }, [data])
}

