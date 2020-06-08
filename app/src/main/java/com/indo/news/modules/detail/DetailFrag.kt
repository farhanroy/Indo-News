package com.indo.news.modules.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.indo.news.R
import com.indo.news.databinding.FragDetailBinding
import com.indo.news.utils.extension.TimeAgo
import com.indo.news.utils.extension.setFragBinding
import dagger.android.support.DaggerFragment

class DetailFrag : DaggerFragment() {

    private lateinit var binding: FragDetailBinding
    private val args: DetailFragArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setFragBinding(R.layout.frag_detail, container)
        binding.data = args.news
        binding.time = TimeAgo.getTimeAgo(args.news.publishedAt)
        binding.content = args.news.content?.slice(0..200)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.seeMore.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(args.news.url)))
        }
    }

}