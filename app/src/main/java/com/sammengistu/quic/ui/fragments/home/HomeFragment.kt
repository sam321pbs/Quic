package com.sammengistu.quic.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sammengistu.quic.data.models.Weather
import com.sammengistu.quic.databinding.FragmentHomeBinding
import com.sammengistu.quic.ui.fragments.BaseFragment
import com.sammengistu.quic.ui.fragments.home.adapters.CardViewAdapter
import com.sammengistu.quic.ui.fragments.home.data.CardViewAdapterItem
import com.sammengistu.quic.ui.fragments.home.viewmodels.HomeViewModel
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
            news.observe(viewLifecycleOwner) { news ->
                if (news?.articles?.isNotEmpty() == true) {
                    adapter.updateList(news.articles as List<CardViewAdapterItem>)
                }
            }
        }

        binding.recyclerView.adapter = adapter
        viewModel.fetchTopNews()
    }
}