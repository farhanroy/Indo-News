package com.indo.news.modules

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.indo.news.R
import com.indo.news.databinding.ActMainBinding
import com.indo.news.utils.setActBinding
import dagger.android.support.DaggerAppCompatActivity

class MainAct : DaggerAppCompatActivity() {

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
            if (destination.id == R.id.splashFrag) {
                bottomNav.visibility = View.GONE
            } else {
                bottomNav.visibility = View.VISIBLE
            }
        }

        bottomNav.setupWithNavController(navController)
    }
}