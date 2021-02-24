package com.garmin.garminkaptain.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.garmin.garminkaptain.R
import com.garmin.garminkaptain.data.PointOfInterest
import com.garmin.garminkaptain.viewModel.PoiViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class PoiMapFragment : Fragment(R.layout.poi_map_fragment), GoogleMap.OnInfoWindowClickListener {

    private val poiViewModel: PoiViewModel by activityViewModels()
    private lateinit var mapFragment: SupportMapFragment
    private var poiList: List<PointOfInterest> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        poiViewModel.getPoiList().also {
            it.observe(viewLifecycleOwner, { list ->
                poiList = list
                refreshMap()
            })
        }
    }

    override fun onInfoWindowClick(selectedMarker: Marker?) {
        selectedMarker?.let { marker ->
            poiList.find {
                it.mapLocation.latitude == marker.position.latitude && it.mapLocation.longitude == marker.position.longitude
            }?.also {
                findNavController(this).navigate(
                    PoiMapFragmentDirections.actionPoiMapFragmentToPoiDetailsFragment(it.id)
                )
            }
        }
    }

    private fun refreshMap() {
        mapFragment.getMapAsync { map ->
            map.setOnInfoWindowClickListener(this)
            val latLngBoundsBuilder = LatLngBounds.builder()
            poiList.forEach { poi ->
                LatLng(poi.mapLocation.latitude, poi.mapLocation.longitude).also {
                    latLngBoundsBuilder.include(it)
                    map.addMarker(MarkerOptions().position(it).title(poi.name))
                }
            }
            map.animateCamera(
                CameraUpdateFactory.newLatLngBounds(latLngBoundsBuilder.build(), PADDING)
            )
        }
    }

    companion object {
        private const val PADDING = 100
    }
}
