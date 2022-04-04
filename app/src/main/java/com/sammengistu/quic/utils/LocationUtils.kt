package com.sammengistu.quic.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices

private const val TAG = "LocationUtils"
const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10F
const val MIN_TIME_BW_UPDATES: Long = 60000L

class LocationUtils {
    companion object {

        @SuppressLint("MissingPermission")
        fun getUserLocation(activity: Activity?, callback: (location: UserLocation?) -> Unit) {
            if (activity != null && locationAccessGranted(activity)) {
                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
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
        @SuppressLint("MissingPermission")
        fun getUserLocation(activity: Activity?, locationListener: LocationListener): UserLocation? {
            try {
                if (activity != null && locationAccessGranted(activity)) {
                    val locationManager = activity.getSystemService(LOCATION_SERVICE) as LocationManager
                    val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                    val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                    if (isGPSEnabled && isNetworkEnabled) {
                        locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES,
                            locationListener
                        )

                        val location = getLastKnownLocation(locationManager)

                        if (location != null) {
                            locationManager.removeUpdates(locationListener)
                            return UserLocation(
                                location.longitude,
                                location.latitude
                            )
                        }
                    }
                }
                return null
            } catch (e: Exception) {
                Log.e(TAG, e.message ?: "Error getting location")
                return null
            }
        }

        @SuppressLint("MissingPermission")
        private fun getLastKnownLocation(locationManager: LocationManager): Location? {
            val providers: List<String> = locationManager.getProviders(true)
            var bestLocation: Location? = null
            for (provider in providers) {
                val l: Location = locationManager.getLastKnownLocation(provider) ?: continue
                if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                    // Found best last known location: %s", l);
                    bestLocation = l
                }
            }
            return bestLocation
        }

        private fun locationAccessGranted(activity: Activity): Boolean {
            val fineLocationPermission = ContextCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION)
            val coarseLocationPermission = ContextCompat.checkSelfPermission(activity, ACCESS_COARSE_LOCATION)
            val granted = fineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                    coarseLocationPermission == PackageManager.PERMISSION_GRANTED

            return granted.also {
                if (!it) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                        101
                    )
                }
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