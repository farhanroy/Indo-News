package com.indo.news.modules.sport

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
import com.indo.news.databinding.FragSportBinding
import com.indo.news.modules.sport.adapter.SportAdapter
import com.indo.news.modules.sport.viewmodel.SportVM
import com.indo.news.utils.Result
import com.indo.news.utils.extension.setFragBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportFrag : Fragment() {

    private val viewModel: SportVM by viewModels()
    private lateinit var binding: FragSportBinding
    private lateinit var sportAdapter: SportAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_sport, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()
        setSwipeRefresh()
    }

    private fun initLiveData() {
        viewModel.getSportNews.observe(viewLifecycleOwner, Observer { result ->
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
        sportAdapter = SportAdapter(requireContext(),news){
            val action = SportFragDirections.actionSportFragToDetailFrag(it)
            findNavController().navigate(action)
        }
        with(binding.rv) {
            adapter = sportAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}