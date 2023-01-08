package com.sammengistu.quic.ui.home.fragments

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sammengistu.quic.databinding.FragmentHomeBinding
import com.sammengistu.quic.ui.home.adapters.CardViewAdapter
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem
import com.sammengistu.quic.ui.home.data.states.*
import com.sammengistu.quic.ui.home.viewmodels.HomeViewModel
import com.sammengistu.quic.utils.LocationUtils
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CardViewAdapter
    private lateinit var swipeLayout: SwipeRefreshLayout

    private val viewModel by viewModels<HomeViewModel>()
    var disableDataFetching = false

    @Inject
    lateinit var locationUtils: LocationUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationUtils.requestLocationPermission(this@HomeFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        swipeLayout = binding.swipeRefreshLayout
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CardViewAdapter()

        setupSwipeLayout()
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupSwipeLayout() {
        swipeLayout.apply {
            setOnRefreshListener { fetchData() }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }

        binding.recyclerView.adapter = adapter
    }

    private fun fetchData() {
        if (disableDataFetching) {
            return
        }
        swipeLayout.isRefreshing = true
        // THIS WILL FETCH DATA AFTER GETTING LOCATION
        locationUtils.getUserLocation()
    }

    private fun setupViewModel() {
        with(viewLifecycleOwner) {
            viewModel.feed.observe(this) { feedState ->
                when(feedState) {
                    is HomeFeedUiState.Success -> {
                        handleSuccessFeedState(feedState)
                    }
                    is HomeFeedUiState.Error -> { onFailedLoadingComplete() }
                }
            }

            locationUtils.locationState.observe(this) {
                when(it) {
                    is LocationState.Success -> { viewModel.fetchFeed(it.userLocation) }
                    is LocationState.NeedsPermission -> {
                        viewModel.fetchFeed(null)
                    }
                    is LocationState.Error -> { viewModel.fetchFeed(null) }
                }
            }
        }

        fetchData()
    }

    private fun handleSuccessFeedState(feedState: HomeFeedUiState.Success) {
        val adapterItems = mutableListOf<CardViewAdapterItem>()
        when(feedState.homeFeedUiItem.weatherUiState) {
            is WeatherUiState.Success -> {
                Timber.d("Success getting weather")
                feedState.homeFeedUiItem.weatherUiState.weather?.let { adapterItems.add(it) }
            }
            is WeatherUiState.Error -> { Timber.e("Error getting weather") }
        }

        when(feedState.homeFeedUiItem.newsUIState) {
            is NewsUIState.Success -> {
                adapterItems.addAll(feedState.homeFeedUiItem.newsUIState.news)
            }
            is NewsUIState.Error -> { Timber.e("Error getting news") }
        }

        when(feedState.homeFeedUiItem.marketUiState) {
            is MarketUiState.Success -> {
                adapterItems.addAll(feedState.homeFeedUiItem.marketUiState.markets)
            }
            is MarketUiState.Error -> { Timber.e("Error getting markets") }
        }

        adapter.addList(adapterItems)
        onSuccessLoadingComplete()
    }

    private fun onSuccessLoadingComplete() {
        swipeLayout.isRefreshing = false
        binding.errorMessage.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun onFailedLoadingComplete() {
        swipeLayout.isRefreshing = false
        binding.errorMessage.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }
}