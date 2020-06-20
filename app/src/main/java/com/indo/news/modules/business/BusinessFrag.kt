package com.indo.news.modules.business

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.indo.news.R
import com.indo.news.data.model.News
import com.indo.news.databinding.FragBusinessBinding
import com.indo.news.modules.business.adapter.BusinessAdapter
import com.indo.news.modules.business.viewmodel.BusinessVM
import com.indo.news.utils.Result
import com.indo.news.utils.extension.setFragBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessFrag : Fragment() {

    private val viewModel: BusinessVM by viewModels()
    private lateinit var binding: FragBusinessBinding
    private lateinit var businessAdapter: BusinessAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_business, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()
        setSwipeRefresh()
    }

    private fun initLiveData() {
        viewModel.getBusinessNews.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Result.Success -> {
                    setHomeAdapter(result.data)
                    binding.isLoading = false
                }
                is Result.InProgress -> binding.isLoading = true
                is Error -> {
                    binding.isLoading = false
                }
            }
        })
    }

    private fun setSwipeRefresh() {
        val swipeRefresh = binding.swipeRefresh
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = true
            initLiveData()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun setHomeAdapter(news: News) {
        businessAdapter = BusinessAdapter(requireContext(), news){
            val action = BusinessFragDirections.actionBusinessFragToDetailFrag(it)
            findNavController().navigate(action)
        }
        with(binding.rv) {
            adapter = businessAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}