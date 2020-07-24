package com.indo.news.modules.favorite.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.indo.news.R
import com.indo.news.databinding.FragFavoriteBinding
import com.indo.news.modules.favorite.adapter.FavoriteRV
import com.indo.news.modules.favorite.viewmodel.FavoriteVM
import com.indo.news.utils.extension.isNull
import com.indo.news.utils.extension.setFragBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFrag : Fragment() {

    private lateinit var binding: FragFavoriteBinding
    private val viewModel: FavoriteVM by viewModels()
    private val favoriteAdapter: FavoriteRV = FavoriteRV()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_favorite, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initLiveData()
    }

    private fun initLiveData() {
        viewModel.getAllFavorite.observe(viewLifecycleOwner, Observer {
            if (it.isNull() || it.isEmpty()) {
                binding.errorLayout.error.isVisible = true
            } else {
                binding.errorLayout.error.isVisible = false
                favoriteAdapter.submitData(it)
            }
        })
    }

    private fun initViews() {
        binding.rvFavorite.adapter = favoriteAdapter
        binding.errorLayout.tvErrorMessage.text = getString(R.string.empty)
        binding.errorLayout.btnRetry.isVisible = false
    }
}