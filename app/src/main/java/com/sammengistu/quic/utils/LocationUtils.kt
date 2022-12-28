package com.sammengistu.quic.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LocationUtils @Inject constructor(
    @ActivityContext private val context: Context
) {

    @SuppressLint("MissingPermission")
    fun getUserLocation(callback: (location: UserLocation?) -> Unit) {
        // Permission is checked here
        if (locationAccessGranted()) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location == null) {
                        callback(location)
                    } else {
                        callback(UserLocation(location.longitude, location.latitude))
                    }
                }
        }
    }

    private fun locationAccessGranted(): Boolean {
        val fineLocationPermission = ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
        val coarseLocationPermission = ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION)
        val granted = fineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                coarseLocationPermission == PackageManager.PERMISSION_GRANTED

        return granted.also { permissionGranted ->
            if (!permissionGranted && context is Activity) {
                ActivityCompat.requestPermissions(
                    context,
                    arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                    101
                )
            }
        }
    }
}

data class UserLocation(
    val longitude: Double,
    val latitude: Double
//    ,
//    val city: String,
//    val state: String
)