package com.wreckingball.design.repositories

import androidx.lifecycle.MutableLiveData
import com.wreckingball.design.models.Sign

class SignRepository {
    var signs = MutableLiveData<List<Sign>>()
    var signMap = mutableMapOf<String, Sign>()

    init {
        signs.value = ArrayList(signMap.values)
    }

    fun addNewSign(sign: Sign) {
        signMap[sign.id] = sign
        signs.value = ArrayList(signMap.values)
    }

    fun getSign(id: String) : Sign? {
        return signMap[id]
    }

    fun deleteSign(id: String) {
        signMap.remove(id)
        signs.value = ArrayList(signMap.values)
    }

    fun getNumSigns() : Int {
        return signMap.size
    }
}