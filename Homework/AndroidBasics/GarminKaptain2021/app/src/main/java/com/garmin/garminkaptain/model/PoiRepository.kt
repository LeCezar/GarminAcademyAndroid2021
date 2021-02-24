package com.garmin.garminkaptain.model

import com.garmin.garminkaptain.data.PointOfInterest
import com.garmin.garminkaptain.data.poiList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object PoiRepository {

    suspend fun getPoiList(): Flow<List<PointOfInterest>> = flow {
        delay(2000)
        emit(poiList)
    }

    suspend fun getPoi(id: Long): Flow<PointOfInterest?> = flow {
        delay(2000)
        emit(poiList.find { it.id == id })
    }

//    fun getPoiMatchingLatLong(lat: Double, long: Double) = flow {
//        delay(2000)
//        emit(poiList.find { it.mapLocation.latitude == lat && it.mapLocation.longitude == long })
//    }
}