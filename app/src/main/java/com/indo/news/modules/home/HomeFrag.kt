package com.indo.news.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.indo.news.R
import com.indo.news.databinding.FragHomeBinding
import com.indo.news.modules.home.adapter.viewpager.HomeVP
import com.indo.news.utils.extension.setFragBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFrag : Fragment() {

    private lateinit var binding: FragHomeBinding
    private lateinit var newsCategory: Array<String>
    private lateinit var homeViewPager: HomeVP

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_home, container)
        newsCategory =  resources.getStringArray(R.array.news_category)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        homeViewPager = HomeVP(requireActivity(), newsCategory)
        viewPager.adapter = homeViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = newsCategory[position]
        }.attach()

    }
}