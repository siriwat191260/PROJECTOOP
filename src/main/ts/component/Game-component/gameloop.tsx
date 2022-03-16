import React from "react"
import { CellStore, renderApi } from "../../stores/Cellstore"
import { ApiDataStore } from "../../api/GameApi"

const Api = ApiDataStore.useState()
const Cell = CellStore.useState()

const gameloop = () => {
    for (let i = 0; i < (Api.virusNum + Api.antiNum); i++) {
        renderApi(Api.type[i], Api.posX[i], Api.posY[i])
    }

}

export default gameloop
