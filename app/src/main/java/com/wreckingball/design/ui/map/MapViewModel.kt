package com.wreckingball.design.ui.map

import androidx.lifecycle.ViewModel
import com.wreckingball.design.components.GMap
import com.wreckingball.design.repositories.SignRepository

class MapViewModel(private val signRepository: SignRepository) : ViewModel() {
    val map = GMap(signRepository)
}