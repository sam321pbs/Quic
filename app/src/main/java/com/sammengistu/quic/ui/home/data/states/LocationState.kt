package com.sammengistu.quic.ui.home.data.states

import com.sammengistu.quic.ui.home.data.UserLocation

sealed class LocationState {
    data class Success(val userLocation: UserLocation): LocationState()
    data class NeedsPermission(val message: String = "Missing Permissions"): LocationState()
    data class Error(val reason: String): LocationState()
}
