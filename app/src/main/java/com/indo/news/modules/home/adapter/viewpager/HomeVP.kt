package com.indo.news.modules.home.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.indo.news.modules.home.view.NewsCategoryFrag

class HomeVP(fm: FragmentActivity, private val newsCategory: Array<String>) :
    FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = newsCategory.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                NewsCategoryFrag.newInstance("")
            }
            1 -> {
                NewsCategoryFrag.newInstance(newsCategory[position])
            }
            2 -> {
                NewsCategoryFrag.newInstance(newsCategory[position])
            }
            3 -> {
                NewsCategoryFrag.newInstance(newsCategory[position])
            }
            else -> {
                NewsCategoryFrag.newInstance("")
            }
        }
    }
}