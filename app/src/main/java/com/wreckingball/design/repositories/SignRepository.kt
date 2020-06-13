package com.wreckingball.design.repositories

import com.wreckingball.design.models.Sign

class SignRepository {
    var signMap = mutableMapOf<String, Sign>()

    fun addNewSign(sign: Sign) {
        signMap[sign.id] = sign
    }

    fun getSign(id: String) : Sign? {
        return signMap[id]
    }

    fun deleteSign(id: String) {
        signMap.remove(id)
    }
}