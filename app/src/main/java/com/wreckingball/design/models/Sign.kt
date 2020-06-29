package com.wreckingball.design.models

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

data class Sign (
    var marker: Marker,
    var id: String,
    val title: String,
    val latLng: LatLng,
    val number: Int = 1,
    val campaignId: String
)