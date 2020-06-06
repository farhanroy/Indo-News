package com.indo.news.modules.headline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.indo.news.R
import com.indo.news.data.model.News
import com.indo.news.databinding.FragHeadlineBinding
import com.indo.news.modules.headline.adapter.HeadlineAdapter
import com.indo.news.modules.headline.viewmodel.HeadlineVM
import com.indo.news.utils.debug
import com.indo.news.utils.setFragBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HeadlineFrag : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HeadlineVM by viewModels {
        viewModelFactory
    }
    private lateinit var binding: FragHeadlineBinding
    private lateinit var headlineAdapter: HeadlineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_headline, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()
    }

    private fun initLiveData() {
        viewModel.getHeadlineNews.observe(viewLifecycleOwner, Observer {
            setHeadlineAdapter(it)
            debug(it.articles.size.toString())
        })
    }

    private fun setHeadlineAdapter(news: News) {
        headlineAdapter = HeadlineAdapter(news) {}
        with(binding.rv) {
            adapter = headlineAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}