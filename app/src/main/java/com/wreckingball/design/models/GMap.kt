package com.wreckingball.design.models

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.wreckingball.design.callbacks.MapsCallback
import com.wreckingball.design.repositories.SignRepository
import com.wreckingball.design.utils.PreferencesWrapper
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class GMap(private val signRepository: SignRepository) : KoinComponent {
    private val preferencesWrapper: PreferencesWrapper by inject()
    private var map: GoogleMap? = null
    lateinit var mapsCallback: MapsCallback

    fun initializeMap(context: Context,
                      fusedLocationProviderClient: FusedLocationProviderClient,
                      googleMap: GoogleMap?) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        map = googleMap

        //get saved camera position
        val camPositionJson = preferencesWrapper.getString("cameraPosition", "")
        var camPosition: CameraPosition? = null
        camPositionJson?.let {json ->
            camPosition = Gson().fromJson(json, CameraPosition::class.java)
        }

        if (camPosition != null) {
            map?.moveCamera(CameraUpdateFactory.newCameraPosition(camPosition))
        } else {
            //initialize camera position to users current location
            map?.isMyLocationEnabled = true
            map?.uiSettings?.isMyLocationButtonEnabled = true
            val task = fusedLocationProviderClient.lastLocation
            task.addOnSuccessListener { location ->
                if (location != null) {
                    map?.animateCamera(CameraUpdateFactory.newLatLng(LatLng(location.latitude, location.longitude)))
                }
            }
        }

        //display zoom controls
        map?.uiSettings?.isZoomControlsEnabled = true

        restoreMarkers()

        map?.setOnMapClickListener { latLng ->
            mapsCallback.onNewMarker(latLng)
        }

        map?.setOnMarkerClickListener {marker ->
            val clickedSign = signRepository.getSign(marker.tag as String)
            clickedSign?.let {sign ->
                mapsCallback.onMarkerClicked(sign)
            }
            true
        }
    }

    fun saveState() {
        val camPosJson = Gson().toJson(map?.cameraPosition)
        preferencesWrapper.putString("cameraPosition", camPosJson)
    }

    fun addMarker(latLng: LatLng, numMarkers: Int) {
        val title = "Sign Location ${signRepository.signMap.size + 1}"
        val newMarker = map?.addMarker(MarkerOptions().position(latLng).title(title))
        newMarker?.let {marker ->
            val id = UUID.randomUUID().toString()
            marker.tag = id
            val sign = Sign(marker, id, title, latLng, numMarkers)
            signRepository.addNewSign(sign)
        }
    }

    fun removeMarker(sign: Sign) {
        signRepository.deleteSign(sign.id)
        sign.marker.remove()
    }

    private fun restoreMarkers() {
        if (map != null) {
            signRepository.signMap.forEach {(_, sign) ->
                val newMarker = map?.addMarker(MarkerOptions().position(sign.latLng).title(sign.title))
                newMarker?.let {marker ->
                    marker.tag = sign.id
                    sign.marker = marker
                }
            }
        }
    }
}
