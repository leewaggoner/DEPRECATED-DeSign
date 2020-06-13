package com.wreckingball.design.ui.home

import androidx.lifecycle.ViewModel
import com.wreckingball.design.models.GMap
import com.wreckingball.design.repositories.SignRepository

class HomeViewModel(private val signRepository: SignRepository) : ViewModel() {
    val map = GMap(signRepository)
}