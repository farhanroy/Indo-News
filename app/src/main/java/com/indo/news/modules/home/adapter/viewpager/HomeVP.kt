package com.indo.news.modules.home.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.indo.news.modules.home.page.HomePage

class HomeVP(fm: FragmentActivity, item: Int): FragmentStateAdapter(fm){
    override fun getItemCount(): Int  = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                HomePage.newInstance()
            }
            1 -> {
                HomePage.newInstance()
            }
            2 -> {
                HomePage.newInstance()
            }
            else -> {
                HomePage.newInstance()
            }
        }
    }

}