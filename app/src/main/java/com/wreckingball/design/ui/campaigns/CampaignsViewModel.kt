package com.wreckingball.design.ui.campaigns

import androidx.lifecycle.ViewModel
import com.wreckingball.design.components.CampaignSigns

class CampaignsViewModel(private val campaignSigns: CampaignSigns) : ViewModel() {
    val campaigns = campaignSigns.campaignRepository.campaigns

    fun getSelectedCampaign() : Int {
        return campaignSigns.campaignRepository.getCurrentCampaignPosition()
    }

    fun changeCampaign(position: Int) {
        campaignSigns.campaignRepository.setNewCampaign(position)
        campaignSigns.signRepository.setNewCampaign(campaignSigns.campaignRepository.getCurrentCampaign()?.id ?: "")
    }
}