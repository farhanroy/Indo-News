package com.indo.news.modules.home.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.indo.news.modules.home.page.HomePage

class HomeVP(fm: FragmentActivity, private val newsCategory: Array<String>) :
    FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = newsCategory.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomePage.newInstance(newsCategory[position])
            }
            1 -> {
                HomePage.newInstance(newsCategory[position])
            }
            2 -> {
                HomePage.newInstance(newsCategory[position])
            }
            else -> {
                HomePage.newInstance(newsCategory[0])
            }
        }
    }
}