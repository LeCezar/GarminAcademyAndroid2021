package com.garmin.garminkaptain.model

import com.garmin.garminkaptain.data.poiList

object PoiRepository {

    fun getPoiList() = poiList

    fun getPoi(id: Long) = poiList.find { it.id == id }

    fun getPoiMatchingLatLong(lat: Double, long: Double) = poiList.find { it.mapLocation.latitude == lat && it.mapLocation.longitude == long }
}