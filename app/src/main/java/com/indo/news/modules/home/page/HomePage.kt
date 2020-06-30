package com.indo.news.modules.home.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.indo.news.R
import com.indo.news.databinding.PageHomeBinding
import com.indo.news.modules.home.adapter.recyclerview.HomePageRV
import com.indo.news.modules.home.adapter.recyclerview.ItemLoadMoreRV
import com.indo.news.modules.home.viewmodel.HomePageVM
import com.indo.news.utils.extension.setFragBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePage : Fragment() {

    private val viewModel: HomePageVM by viewModels()
    private lateinit var binding: PageHomeBinding
    private val homePageAdapter = HomePageRV()
    private val category: String
        get() = arguments?.getString("category", "") ?: ""
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.page_home, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setHomePageAdapter()
        setNews()
        setSwipeRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    private fun initViews() {
        binding.errorLayout.btnRetry.setOnClickListener {
            setNews()
        }
    }

    private fun setNews() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.getNewsByCategory(category).collectLatest {
                homePageAdapter.submitData(it)
            }
        }
    }

    private fun setHomePageAdapter() {
        binding.rv.adapter = homePageAdapter.withLoadStateHeaderAndFooter(
            header = ItemLoadMoreRV { homePageAdapter.retry() },
            footer = ItemLoadMoreRV { homePageAdapter.retry() }
        )
        homePageAdapter.addLoadStateListener { loadState ->
            binding.rv.isVisible = loadState.refresh is LoadState.NotLoading
            binding.loadingLayout.loading.isVisible = loadState.refresh is LoadState.Loading
            binding.errorLayout.error.isVisible = loadState.refresh is LoadState.Error
        }
    }

    private fun setSwipeRefresh() {
        val swipeRefresh = binding.swipeRefresh
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            setNews()
            swipeRefresh.isRefreshing = false
        }
    }

    companion object {
        fun newInstance(category: String) = HomePage().apply {
            arguments = bundleOf("category" to category)
        }
    }
}