package com.wreckingball.design.models

import com.google.android.gms.maps.model.Marker

data class Sign (
    var marker: Marker,
    var markerId: String,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val number: Int = 1,
    val campaignId: String
)