package com.sammengistu.quic.ui.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sammengistu.quic.databinding.FragmentHomeBinding
import com.sammengistu.quic.ui.home.adapters.CardViewAdapter
import com.sammengistu.quic.ui.home.data.CardViewAdapterItem
import com.sammengistu.quic.ui.home.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CardViewAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }

        with(viewModel) {
            news.observe(viewLifecycleOwner) { articles ->
                // todo: handle null
                adapter.updateList(articles as List<CardViewAdapterItem>)
            }

            weather.observe(viewLifecycleOwner) { weather ->
                // todo: handle null
                adapter.addItem(weather as CardViewAdapterItem)
            }

            finance.observe(viewLifecycleOwner) { summary ->
                Log.d("HomeFrag", summary?.marketSummaryResponse?.result.toString())
            }
        }

        binding.recyclerView.adapter = adapter
        viewModel.fetchTopNews()
        viewModel.fetchCurrentWeather(activity)
        viewModel.fetchMarketSummary()
    }
}