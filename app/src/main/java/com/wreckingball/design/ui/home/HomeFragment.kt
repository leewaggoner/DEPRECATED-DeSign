package com.wreckingball.design.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.wreckingball.design.LoginActivity
import com.wreckingball.design.R
import com.wreckingball.design.auth.Authentication
import com.wreckingball.design.callbacks.MapsCallback
import com.wreckingball.design.models.Sign
import kotlinx.android.synthetic.main.dialog_add_marker.view.*
import kotlinx.android.synthetic.main.dialog_marker_info.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home), OnMapReadyCallback {
    private val model: HomeViewModel by viewModel()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get notified when the map is ready to be used.
        val fragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(fragment.requireActivity())
        fragment.getMapAsync(this)

        model.map.mapsCallback = object : MapsCallback {
            override fun onNewMarker(latLng: LatLng) {
                val addMarkerView = requireActivity().layoutInflater.inflate(R.layout.dialog_add_marker, null)
                AlertDialog.Builder(requireContext())
                    .setView(addMarkerView)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        val numMarkers = addMarkerView.marker_number_spinner.selectedItem.toString()
                        model.map.addMarker(latLng, numMarkers.toInt())
                    }
                    .setNegativeButton(android.R.string.cancel, null)
                    .create()
                    .show()
            }

            override fun onMarkerClicked(sign: Sign) {
                val markerInfoView = requireActivity().layoutInflater.inflate(R.layout.dialog_marker_info, null)
                markerInfoView.delete_marker_message.text = getString(R.string.marker_info_message).format(sign.number, sign.title)
                AlertDialog.Builder(requireContext())
                    .setView(markerInfoView)
                    .setPositiveButton(android.R.string.cancel, null)
                    .setNegativeButton(R.string.delete) { _, _ ->
                        model.map.removeMarker(sign)
                    }
                    .create()
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        model.map.saveState()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        model.map.initializeMap(requireContext(), fusedLocationProviderClient, googleMap)
    }
}
