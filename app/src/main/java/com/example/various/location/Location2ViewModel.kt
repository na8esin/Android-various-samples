package com.example.various.location

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Location2ViewModel(
    private val fusedLocationClient: FusedLocationProviderClient
) : ViewModel() {
    private var _location = MutableLiveData<Location>()
    val location: LiveData<Location>
        get() = _location

    fun lastLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            // Call requires permission which may be rejected by user: code should explicitly check to see if permission is available (with checkPermission) or explicitly handle a potential SecurityExceptio
            try {
                val locationResult = fusedLocationClient.lastLocation
                _location.postValue(Tasks.await(locationResult))
            } catch (e: SecurityException) {
                // Request permission
                Log.d(TAG, "Request permission")
            }
        }
    }

    companion object {
        const val TAG = "Location2ViewModel"
    }
}

class Location2ViewModelFactory(
    private val fusedLocationClient: FusedLocationProviderClient
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Location2ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return Location2ViewModel(fusedLocationClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}