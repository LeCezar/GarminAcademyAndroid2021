package com.garmin.garminkaptain.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.garmin.garminkaptain.TAG
import com.garmin.garminkaptain.data.PointOfInterest
import com.garmin.garminkaptain.model.PoiRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class PoiViewModel : ViewModel() {

    init {
        Log.d(TAG, "init called")
    }

    private val poiListLiveData: MutableLiveData<List<PointOfInterest>> by lazy {
        MutableLiveData<List<PointOfInterest>>()
    }

    private val loadingLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun getPoi(id: Long): LiveData<PointOfInterest?> = liveData {
        loadingLiveData.postValue(true)
        PoiRepository.getPoi(id).collect {
            emit(it)
            loadingLiveData.postValue(false)
        }
    }

//    fun getPoiWithMatchingLatLong(lat: Double, long: Double): LiveData<PointOfInterest> {
//        loadPoiMatchingLatLong(lat, long)
//        return poiLiveData
//    }

    fun getPoiList(): LiveData<List<PointOfInterest>> {
        loadPoiList()
        return poiListLiveData
    }

    fun getLoading(): LiveData<Boolean> = loadingLiveData

    fun loadPoiList() {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            PoiRepository.getPoiList().collect {
                poiListLiveData.postValue(it)
                loadingLiveData.postValue(false)
            }
        }
    }

//    private fun loadPoiMatchingLatLong(lat: Double, long: Double) {
//        poiLiveData.postValue(PoiRepository.getPoiMatchingLatLong(lat, long))
//    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared() called")
    }

}
