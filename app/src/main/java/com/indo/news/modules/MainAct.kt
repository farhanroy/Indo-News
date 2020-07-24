package com.indo.news.modules

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.indo.news.R
import com.indo.news.databinding.ActMainBinding
import com.indo.news.utils.extension.setActBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainAct : AppCompatActivity() {

    private lateinit var binding: ActMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initBottomNav()
    }

    private fun initBinding() {
        binding = setActBinding(R.layout.act_main)
    }

    private fun initBottomNav() {
        val navController = Navigation.findNavController(this, R.id.main_fragment)
        val bottomNav = binding.bottomNavigationView

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNav.isVisible = destination.id == R.id.homeFrag || destination.id == R.id.favoriteFrag
        }

        bottomNav.setupWithNavController(navController)
    }
}