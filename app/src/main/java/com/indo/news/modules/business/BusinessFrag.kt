package com.indo.news.modules.business

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
import com.indo.news.databinding.FragBusinessBinding
import com.indo.news.modules.business.adapter.BusinessAdapter
import com.indo.news.modules.business.viewmodel.BusinessVM
import com.indo.news.utils.Result
import com.indo.news.utils.constant.Constants
import com.indo.news.utils.extension.setFragBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BusinessFrag : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: BusinessVM by viewModels {
        viewModelFactory
    }
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

    private fun setHomeAdapter(news: News) {
        businessAdapter = BusinessAdapter(news){
            val action = BusinessFragDirections.actionBusinessFragToDetailFrag(it)
            findNavController().navigate(action)
        }
        with(binding.rv) {
            adapter = businessAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}