package com.wreckingball.design.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wreckingball.design.components.CampaignSigns
import com.wreckingball.design.models.Sign

class DashboardViewModel(private val campaignSigns: CampaignSigns) : ViewModel() {
    val signs: MutableLiveData<List<Sign>> = campaignSigns.signRepository.campaignSigns

    fun handleSwipeLeft(position: Int) {
        signs.value?.let {signList ->
            val sign = signList[position]
            campaignSigns.signRepository.deleteSign(sign.markerId)
        }
    }
}