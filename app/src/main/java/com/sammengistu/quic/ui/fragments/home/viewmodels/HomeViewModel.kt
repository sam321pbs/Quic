package com.sammengistu.quic.ui.fragments.home.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sammengistu.quic.data.models.news.News
import com.sammengistu.quic.data.source.Result
import com.sammengistu.quic.data.source.news.NewsConstants
import com.sammengistu.quic.data.source.news.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    private val _news = MutableLiveData<News?>()
    val news = _news as LiveData<News?>

    fun fetchTopNews() {
        viewModelScope.launch {
            when(val result = repository.getTopNews(NewsConstants.Country.US, NewsConstants.Size.MAX)) {
                is Result.Success -> {
                    _news.value = result.data
                }
                is Result.Error -> {
                    Log.e("HomeViewModel", result.exception.toString())
                }
            }
        }
    }
}