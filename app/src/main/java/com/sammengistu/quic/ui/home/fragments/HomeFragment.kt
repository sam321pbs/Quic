package com.sammengistu.quic.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sammengistu.quic.databinding.FragmentHomeBinding
import com.sammengistu.quic.ui.home.adapters.CardViewAdapter
import com.sammengistu.quic.ui.home.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CardViewAdapter
    private lateinit var swipeLayout: SwipeRefreshLayout

    private val viewModel by viewModels<HomeViewModel>()

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

    override fun onResume() {
        super.onResume()
        fetchData()
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
        swipeLayout.isRefreshing = true
        adapter.clearList()
        viewModel.fetchTopNews()
        viewModel.fetchCurrentWeather(activity)
        viewModel.fetchMarketSummary()
    }

    private fun setupViewModel() {
        with(viewModel) {
            news.observe(viewLifecycleOwner) { articles ->
                if (articles != null) {
                    adapter.addList(articles)
                }
                onLoadingComplete()
            }

            weather.observe(viewLifecycleOwner) { weather ->
                if (weather != null) {
                    adapter.addItem(weather)
                }
                onLoadingComplete()
            }

            finance.observe(viewLifecycleOwner) { markets ->
                if (markets != null) {
                    adapter.addList(markets)
                }
                onLoadingComplete()
            }
        }
    }

    private fun onLoadingComplete() {
        swipeLayout.isRefreshing = false
        binding.errorMessage.visibility = View.GONE
    }
}