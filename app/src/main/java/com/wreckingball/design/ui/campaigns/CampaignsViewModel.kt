package com.wreckingball.design.ui.campaigns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wreckingball.design.repositories.CampaignRepository
import com.wreckingball.design.repositories.SignRepository

class CampaignsViewModel(private val campaignRepository: CampaignRepository,
                         private val signRepository: SignRepository) : ViewModel() {
    val campaigns = campaignRepository.campaigns

    fun getSelectedCampaign() : Int {
        return campaignRepository.getCurrentCampaignPosition()
    }

    fun changeCampaign(position: Int) {
        campaignRepository.setNewCampaign(position)
        signRepository.setNewCampaign(campaignRepository.getCurrentCampaign()?.id ?: "")
    }
}