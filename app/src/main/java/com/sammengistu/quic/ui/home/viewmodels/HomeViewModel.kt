package com.sammengistu.quic.ui.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sammengistu.quic.ui.home.usecase.GetFeedUseCase
import com.sammengistu.quic.utils.UserLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val feedUseCase: GetFeedUseCase,
) : ViewModel() {

    val feed = feedUseCase.feedState

    fun fetchFeed(location: UserLocation?) {
        viewModelScope.launch { feedUseCase.loadFeed(location) }
    }
}