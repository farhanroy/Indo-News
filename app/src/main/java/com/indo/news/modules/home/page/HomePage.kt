package com.indo.news.modules.home.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.indo.news.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.page_home, container, false)
    }

    companion object {
        fun newInstance(category: String) = HomePage().apply {
            arguments = bundleOf("category" to category)
        }
    }
}