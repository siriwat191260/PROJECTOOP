import axios from 'axios'
import { useState , useEffect } from 'react';

const API_URL = 'http://localhost:8080/bodyData'

export type BodyData = {
    m : number
    n : number
    antiCredit : number
    type : number[]
    posX : number[]
    posY : number[]
    order : number[]
    antiCreditCost : number
    antiNum : number
    virusNum : number
    antiHealth : number
    virusHealth : number
}

const receiveData = () => {
    const [pause,setPause] = useState<boolean>(false);
    const [data,setData] = useState<BodyData>();
    const [m,setM] = useState<number>();
    const [n,setN] = useState<number>();
    const [antiCredit,setAntiCredit] = useState<number>();
    const [type,setType] = useState<number[]>();
    const [posX,setPosX] = useState<number[]>();
    const [posY,setPosY] = useState<number[]>();
    const [order,setOrder] = useState<number[]>();
    const [antiCreditCost,setAntiCreditCost] = useState<number>();
    const [antiNum,setAntiNum] = useState<number>();
    const [virusNum,setVirusNum] = useState<number>();
    const [antiHealth,setAntiHealth] = useState<number>();
    const [virusHealth,setVirusHealth] = useState<number>();


    const fetchData = async () =>{
        try{
            const resp = await axios.get<BodyData>(API_URL)
            if(data != resp.data){
                setData(resp.data)
                console.log(data)
            }
        }
        catch(err){
            console.log(err)
        }
    }

    useEffect(()=>{
        setInterval(()=>{
            fetchData()
        },(1000))
    },[])

    useEffect(()=>{
        if(pause != true){
            if(data != null){
                setM(data.m)
                setN(data.n)
                setAntiCredit(data.antiCredit)
                setType(data.type)
                setPosX(data.posX)
                setPosY(data.posY)
                setOrder(data.order)
                setAntiCreditCost(data.antiCreditCost)
                setAntiNum(data.antiNum)
                setVirusNum(data.virusNum)
                setAntiHealth(data.antiHealth)
                setVirusHealth(data.virusHealth)
                
                console.log("M :"+ m)
                console.log("N :"+ n)
                console.log("AntiCredit :"+ antiCredit)
                console.log("Type :"+ type)
                console.log("PosX :"+ posX)
                console.log("PosY :"+ posY)
                console.log("Order :"+ order)
                console.log("AntiCreditCost :"+ antiCreditCost)
                console.log("AntiNum :"+ antiNum)
                console.log("VirusNum :"+ virusNum)
                console.log("AntiHealth :"+ antiHealth)
                console.log("VirusHealth :"+ virusHealth)

            }
        }
        
    },[data])

}


export default receiveData;