package com.sammengistu.quic.ui.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sammengistu.quic.databinding.FragmentHomeBinding
import com.sammengistu.quic.ui.home.adapters.CardViewAdapter
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem
import com.sammengistu.quic.ui.home.data.states.HomeFeedUiState
import com.sammengistu.quic.ui.home.data.states.MarketUiState
import com.sammengistu.quic.ui.home.data.states.NewsUIState
import com.sammengistu.quic.ui.home.data.states.WeatherUiState
import com.sammengistu.quic.ui.home.viewmodels.HomeViewModel
import com.sammengistu.quic.utils.LocationUtils
import dagger.hilt.android.AndroidEntryPoint
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

    companion object {
        const val TAG = "HomeFragment"
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
        locationUtils.getUserLocation {
            viewModel.fetchFeed(it)
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            feed.observe(viewLifecycleOwner) { feedState ->
//                Log.d(TAG, "Counter ${count}")
                Log.d(TAG, "collect feed state")
                when(feedState) {
                    is HomeFeedUiState.Success -> {
                        handleSuccessFeedState(feedState)
                    }
                }
                onLoadingComplete()
            }
        }

//        viewModel.counter.onEach {
//            Log.d(TAG, "Counter ${it}")
//            onLoadingComplete()
//        }.launchIn(viewLifecycleOwner.lifecycleScope)

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {




//                viewModel.feed.collect { feedState ->
//                    Log.d(TAG, "collect feed state")
//                    when(feedState) {
//                        is HomeFeedUiState.Success -> {
//                            handleSuccessFeedState(feedState)
//                        }
//                    }
//                    onLoadingComplete()
//                }
//            }
//        }
        fetchData()
    }

    private fun handleSuccessFeedState(feedState: HomeFeedUiState.Success) {
        val adapterItems = mutableListOf<CardViewAdapterItem>()
        when(feedState.homeFeedUiItem.weatherUiState) {
            is WeatherUiState.Success -> {
                Log.d(TAG, "Success loading")
                feedState.homeFeedUiItem.weatherUiState.weather?.let { adapterItems.add(it) }
            }
        }

        when(feedState.homeFeedUiItem.newsUIState) {
            is NewsUIState.Success -> {
                adapterItems.addAll(feedState.homeFeedUiItem.newsUIState.news)
            }
        }

        when(feedState.homeFeedUiItem.marketUiState) {
            is MarketUiState.Success -> {
                adapterItems.addAll(feedState.homeFeedUiItem.marketUiState.markets)
            }
        }
        Log.d(TAG, "Items = ${adapterItems.size}")
        adapter.addList(adapterItems)
        onLoadingComplete()
    }

    private fun onLoadingComplete() {
        swipeLayout.isRefreshing = false
        binding.errorMessage.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }
}