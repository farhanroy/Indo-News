package com.indo.news.modules.entertainment

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
import com.indo.news.databinding.FragEntertainmentBinding
import com.indo.news.modules.entertainment.adapter.EntertainmentAdapter
import com.indo.news.modules.entertainment.viewmodel.EntertainmentVM
import com.indo.news.utils.Result
import com.indo.news.utils.extension.setFragBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntertainmentFrag : Fragment() {

    private val viewModel: EntertainmentVM by viewModels()
    private lateinit var binding: FragEntertainmentBinding
    private lateinit var entertainmentAdapter: EntertainmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_entertainment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()
        setSwipeRefresh()
    }

    private fun initLiveData() {
        viewModel.getEntertainmentNews.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Result.Success -> {
                    setHeadlineAdapter(result.data)
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

    private fun setHeadlineAdapter(news: News) {
        entertainmentAdapter = EntertainmentAdapter(requireContext(), news) {
            val action = EntertainmentFragDirections.actionEntertainmentFragToDetailFrag(it)
            findNavController().navigate(action)
        }
        with(binding.rv) {
            adapter = entertainmentAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}