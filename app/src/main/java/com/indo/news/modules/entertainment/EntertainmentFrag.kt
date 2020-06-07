package com.indo.news.modules.entertainment

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
import com.indo.news.databinding.FragEntertainmentBinding
import com.indo.news.modules.entertainment.adapter.EntertainmentAdapter
import com.indo.news.modules.entertainment.viewmodel.EntertainmentVM
import com.indo.news.utils.Result
import com.indo.news.utils.constant.Constants
import com.indo.news.utils.extension.setFragBinding
import dagger.android.support.DaggerFragment
import java.lang.Error
import javax.inject.Inject

class EntertainmentFrag : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: EntertainmentVM by viewModels {
        viewModelFactory
    }
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

    private fun setHeadlineAdapter(news: News) {
        entertainmentAdapter = EntertainmentAdapter(news) {
            val bundle = bundleOf(Constants.TO_DETAIL to it)
            findNavController().navigate(R.id.action_entertainmentFrag_to_detailFrag, bundle)
        }
        with(binding.rv) {
            adapter = entertainmentAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}