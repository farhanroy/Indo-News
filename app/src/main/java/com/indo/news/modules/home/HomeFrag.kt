package com.indo.news.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.indo.news.R
import com.indo.news.data.model.News
import com.indo.news.databinding.FragHomeBinding
import com.indo.news.modules.home.adapter.HomeAdapter
import com.indo.news.modules.home.viewmodel.HomeVM
import com.indo.news.utils.Result
import com.indo.news.utils.constant.Constants
import com.indo.news.utils.extension.setFragBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFrag : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeVM by viewModels {
        viewModelFactory
    }
    private lateinit var binding: FragHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_home, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()
    }

    private fun initLiveData() {
        viewModel.getHomeNews.observe(viewLifecycleOwner, Observer { result ->
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

    private fun setHomeAdapter(news: News) {
        homeAdapter = HomeAdapter(news){
            val bundle = bundleOf(Constants.TO_DETAIL to it)
            findNavController().navigate(R.id.action_homeFrag_to_detailFrag, bundle)
        }
        with(binding.rv) {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}