package com.wreckingball.design.components

import com.wreckingball.design.repositories.CampaignRepository
import com.wreckingball.design.repositories.SignRepository

class CampaignSigns(val campaignRepository: CampaignRepository, val signRepository: SignRepository) {
    fun initialize() {
        campaignRepository.initialize()
        signRepository.setNewCampaign(campaignRepository.getCurrentCampaign()?.id ?: "")
    }

    fun clear() {
        campaignRepository.clear()
        signRepository.clear()
    }
}