package com.wreckingball.design.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Campaign
import com.wreckingball.design.utils.PreferencesWrapper

const val CURRENT_CAMPAIGN_KEY = "currentCampaign"
const val TAG = "CampaignRepository"

class CampaignRepository(private val preferencesWrapper: PreferencesWrapper) {
    val campaigns = MutableLiveData<List<Campaign>>()
    private val currentCampaign: String
        get() {
            return preferencesWrapper.getString(CURRENT_CAMPAIGN_KEY, "")
        }

    fun initialize() {
        initializeData()
    }

    private fun initCampaignId(campaignId: String) {
        if (currentCampaign.isEmpty()) {
            preferencesWrapper.putString(CURRENT_CAMPAIGN_KEY, campaignId ?: "")
        }
    }

    private fun initializeData() {
        Amplify.DataStore.query(Campaign::class.java,
            {
                val campaignList = ArrayList<Campaign>()
                if (it.hasNext()) {
                    while (it.hasNext()) {
                        campaignList.add(it.next())
                    }
                    campaigns.postValue(campaignList)
                } else {
                    createInitialData()
                }
            },
            {
                Log.e(TAG, "Campaign query failed!")
            }
        )
    }

    private fun createInitialData() {
        val campaignList = ArrayList<Campaign>()
        campaignList.add(Campaign.builder().name("Jim for Congress").description("Re-elect Jim!").build())
        campaignList.add(Campaign.builder().name("1224 Grant St. Home Sale").description("Let's sell this one today!").build())
        Amplify.DataStore.save(campaignList[0],
            { Log.i(TAG, "Saved " + campaignList[0].name) },
            { Log.e(TAG, "Save failed!", it) }
        )
        Amplify.DataStore.save(campaignList[1],
            { Log.i(TAG, "Saved " + campaignList[1].name) },
            { Log.e(TAG, "Save failed!", it) }
        )
        campaigns.value = campaignList
        initCampaignId(campaignList[0].id)

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

    fun clear() {
        campaigns.value = mutableListOf()
        preferencesWrapper.putString(CURRENT_CAMPAIGN_KEY, "")
    }
}