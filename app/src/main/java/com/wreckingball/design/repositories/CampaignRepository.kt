package com.wreckingball.design.repositories

import androidx.lifecycle.MutableLiveData
import com.wreckingball.design.models.Campaign
import com.wreckingball.design.utils.PreferencesWrapper

const val CURRENT_CAMPAIGN_KEY = "currentCampaign"

class CampaignRepository(private val preferencesWrapper: PreferencesWrapper) {
    val campaigns = MutableLiveData<List<Campaign>>()
    private val currentCampaign: String
        get() {
            return preferencesWrapper.getString(CURRENT_CAMPAIGN_KEY, "")
        }

    init {
        val loadedCampaigns = listOf<Campaign>(
            Campaign("001", "Jim for Congress", "Re-elect Jim!"),
            Campaign("002", "1224 Grant St. Home Sale", "Let's sell this one today!"))
        campaigns.value = loadedCampaigns
        if (currentCampaign.isEmpty()) {
            preferencesWrapper.putString(CURRENT_CAMPAIGN_KEY, campaigns.value?.get(0)?.id ?: "")
        }
    }

    fun setNewCampaign(campaignId: Int) {
        val campaign = campaigns.value?.get(campaignId)
        campaign?.id.let {id ->
            if (!id.isNullOrEmpty()) {
                preferencesWrapper.putString(CURRENT_CAMPAIGN_KEY, id)
            }
        }
    }

    fun getCurrentCampaignPosition() : Int {
        campaigns.value.let {
            for ((index, value) in campaigns.value!!.withIndex()) {
                if (value.id == currentCampaign) {
                    return index
                }
            }
        }
        return 0
    }

    fun getCurrentCampaign() : Campaign? {
        campaigns.value?.forEach() {campaign ->
            if (campaign.id == currentCampaign) {
                return campaign
            }
        }
        return null
    }
}