package com.sammengistu.quic.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import com.sammengistu.quic.ui.home.data.UserLocation
import com.sammengistu.quic.ui.home.data.states.LocationState
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class LocationUtils @Inject constructor(
    @ActivityContext private val context: Context
) {

    private val _locationState = MutableLiveData<LocationState>()
    val locationState = _locationState as LiveData<LocationState>

    @SuppressLint("MissingPermission")
    fun getUserLocation() {
        // Permission is checked here
        if (locationAccessGranted()) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location == null) {
                        _locationState.value = LocationState.Error("Location was null")
                    } else {
                        _locationState.value = LocationState.Success(
                            UserLocation(location.longitude, location.latitude)
                        )
                    }
                }
        } else {
            _locationState.value = LocationState.NeedsPermission()
        }
    }

    fun requestLocationPermission(fragment: Fragment) {
        try {
            val locationPermissionRequest = fragment.registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                when {
                    permissions.getOrDefault(ACCESS_FINE_LOCATION, false) ||
                            permissions.getOrDefault(ACCESS_COARSE_LOCATION, false) -> {
                        getUserLocation()
                    }
                    else -> {
                        // No location access granted.
                    }
                }
            }

            locationPermissionRequest.launch(
                arrayOf(
                    ACCESS_FINE_LOCATION,
                    ACCESS_COARSE_LOCATION
                )
            )
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    private fun locationAccessGranted(): Boolean {
        val fineLocationPermission =
            ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
        val coarseLocationPermission =
            ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION)

        return fineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                coarseLocationPermission == PackageManager.PERMISSION_GRANTED
    }
}