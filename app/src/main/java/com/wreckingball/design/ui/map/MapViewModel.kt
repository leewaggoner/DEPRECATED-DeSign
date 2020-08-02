package com.wreckingball.design.ui.map

import androidx.lifecycle.ViewModel
import com.wreckingball.design.components.CampaignSigns
import com.wreckingball.design.components.GMap

class MapViewModel(campaignSigns: CampaignSigns) : ViewModel() {
    val map = GMap(campaignSigns)
}