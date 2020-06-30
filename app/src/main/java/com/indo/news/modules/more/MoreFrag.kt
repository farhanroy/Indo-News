package com.indo.news.modules.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.indo.news.databinding.FragMoreBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoreFrag : Fragment() {

    private lateinit var binding: FragMoreBinding
    private val args: MoreFragArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragMoreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
    }

    private fun initWebView() {
        val webView = binding.webView

        webView.loadUrl(args.url)
        webView.settings.apply {
            javaScriptEnabled = false
            allowContentAccess = true
            useWideViewPort = true
            loadsImagesAutomatically = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            domStorageEnabled = false
        }
    }
}