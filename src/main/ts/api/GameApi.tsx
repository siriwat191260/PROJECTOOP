import axios from 'axios'
import { Store } from "pullstate";
import { useState, useEffect } from 'react';
import { renderApi, updatemn } from '../stores/Cellstore';

const API_URL = 'http://localhost:8080/bodyData'
const timer_URL = 'http://localhost:8080/countData'

let pause: boolean = false
let timeapi: number = 5
let game: boolean = false

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

export const settimeapi = () =>{
    return timeapi
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

export const endgame =() => {
    let ans:number
    ApiDataStore.update(s => {
       
        if(game){
        if(s.antiNum == 0 && s.virusNum>0 ){
            ans = 1 
        }else if(s.virusNum == 0 && s.antiNum>0){
            ans = 2
        }
        }else if(s.antiNum>0 && s.virusNum>0) {
            game = true
        }
    
    })
    return ans
}

export const receiveData = () => {
        const [data, setData] = useState<BodyData>();
        const [timerData, setTimerData] = useState<number>();
     
    //fetch timer    
    const fetchCount = async () => {
            try {
                const resp = await axios.get<number>(timer_URL)
                if (timerData != resp.data) {
                    setTimerData(resp.data)
                    console.log(resp.data)
                }

            } catch (err) {
                console.log(err)
            }
        }
    
    //fetch data
    const fetchData = async () => {
        try {
            const resp = await axios.get<BodyData>(API_URL)
            if (data != resp.data) {
                setData(resp.data)
                console.log(data)
            }

        }
        catch (err) {
            console.log(err)
        }
    }

    useEffect(() => {
        setInterval(() => {
            fetchCount()
            fetchData()
            
        }, (500))
    }, [])

    useEffect(() => {
        if (pause != true) {
            if (data != null ) {
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

                    updatemn(data.m, data.n)

                    let x = 0;
                    data.order.forEach(() => {
                        renderApi(data.type[x], data.posX[x], data.posY[x] - 1)
                        x += 1
                    });

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

    useEffect(()=>{
        if(pause!=true){
            if(timerData != null){
                    timeapi = timerData
                    console.log("Timer : "+ timerData)
            }
        }else {
            console.log("pause")
        }
    }, [1000])

    

}

