package com.wreckingball.design.repositories

import androidx.lifecycle.MutableLiveData
import com.wreckingball.design.models.Sign
import com.wreckingball.design.utils.PreferencesWrapper

class SignRepository(private val preferencesWrapper: PreferencesWrapper) {
    val campaignSigns = MutableLiveData<List<Sign>>()
    private lateinit var currentCampaign: String
    private var signMap = mutableMapOf<String, Sign>()

    fun setNewCampaign(campaignId: String) {
        currentCampaign = campaignId
        updateCampaignSigns()
    }

    fun addNewSign(sign: Sign) {
        signMap[sign.markerId] = sign
        updateCampaignSigns()
    }

    fun getSign(id: String) : Sign? {
        return signMap[id]
    }

    fun deleteSign(id: String) {
        signMap.remove(id)
        updateCampaignSigns()
    }

    fun getNumSigns() : Int {
        return campaignSigns.value?.size ?: 0
    }

    private fun updateCampaignSigns() {
        currentCampaign = preferencesWrapper.getString(CURRENT_CAMPAIGN_KEY, "")
        val list = mutableListOf<Sign>()
        signMap.forEach {(_, sign) ->
            if (sign.campaignId == currentCampaign) {
                list.add(sign.copy())
            }
        }
        campaignSigns.value = list
    }

    fun clear() {
        campaignSigns.value = mutableListOf()
        currentCampaign = ""
        signMap = mutableMapOf()
    }
}