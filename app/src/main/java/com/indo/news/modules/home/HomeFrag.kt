package com.indo.news.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.indo.news.R
import com.indo.news.databinding.FragHomeBinding
import com.indo.news.modules.home.viewmodel.HomePageVM
import com.indo.news.utils.extension.setFragBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFrag : Fragment() {

    private val viewModel: HomePageVM by viewModels()
    private lateinit var binding: FragHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_home, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}