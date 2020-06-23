package com.indo.news.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.indo.news.R
import com.indo.news.databinding.FragHomeBinding
import com.indo.news.modules.home.adapter.HomeAdapter
import com.indo.news.modules.home.adapter.ItemLoadMoreAdapter
import com.indo.news.modules.home.viewmodel.HomeVM
import com.indo.news.utils.extension.setFragBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFrag : Fragment() {

    private val viewModel: HomeVM by viewModels()
    private lateinit var binding: FragHomeBinding
    private val homeAdapter = HomeAdapter()

    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_home, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeAdapter()
        setHomeNews()
        setSwipeRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    private fun setHomeNews() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.getHomeNews().collectLatest {
                homeAdapter.submitData(it)
            }
        }
    }

    private fun setHomeAdapter() {
        binding.rv.adapter = homeAdapter.withLoadStateHeaderAndFooter(
            header = ItemLoadMoreAdapter { homeAdapter.retry() },
            footer = ItemLoadMoreAdapter { homeAdapter.retry() }
        )
        homeAdapter.addLoadStateListener { loadState ->
            binding.rv.isVisible = loadState.refresh is LoadState.NotLoading
            binding.loadingLayout.loading.isVisible = loadState.refresh is LoadState.Loading
            binding.errorLayout.error.isVisible = loadState.refresh is LoadState.Error
        }
    }

    private fun setSwipeRefresh() {
        val swipeRefresh = binding.swipeRefresh
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            setHomeNews()
            swipeRefresh.isRefreshing = false
        }
    }

}