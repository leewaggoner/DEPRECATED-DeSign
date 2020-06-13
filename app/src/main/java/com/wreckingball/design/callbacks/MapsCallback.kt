package com.wreckingball.design.callbacks

import com.google.android.gms.maps.model.LatLng
import com.wreckingball.design.models.Sign

interface MapsCallback {
    fun onNewMarker(latLng: LatLng)
    fun onMarkerClicked(sign: Sign)
}