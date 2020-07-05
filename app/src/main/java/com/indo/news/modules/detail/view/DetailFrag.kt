package com.indo.news.modules.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.indo.news.R
import com.indo.news.databinding.FragDetailBinding
import com.indo.news.modules.detail.DetailFragArgs
import com.indo.news.modules.detail.DetailFragDirections
import com.indo.news.modules.detail.adapter.RecommendedRV
import com.indo.news.modules.detail.viewmodel.DetailVM
import com.indo.news.utils.Result
import com.indo.news.utils.extension.TimeAgo
import com.indo.news.utils.extension.setFragBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFrag : Fragment() {

    private lateinit var binding: FragDetailBinding
    private lateinit var recommendedAdapter: RecommendedRV
    private val viewModel: DetailVM by viewModels()
    private val args: DetailFragArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_detail, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAdapter()
    }

    private fun initView() {
        binding.detail.apply {
            data = args.news
            time = TimeAgo.getTimeAgo(args.news.publishedAt)
            content = args.news.content?.slice(0..200)
            tvSeeMore.setOnClickListener {
                val action = DetailFragDirections.actionDetailFragToMoreFrag(args.news.url)
                findNavController().navigate(action)
            }
        }
    }

    private fun initLiveData() {
        viewModel.getRecommendedNews.observe(this, Observer {})
    }

    private fun initAdapter() {}

}