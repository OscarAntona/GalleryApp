package com.antgut.myapplication.features.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.antgut.myapplication.R
import com.antgut.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setupView()
        screenSplash.setKeepOnScreenCondition{ false }
    }

    private fun setupView() {
        setupBinding()
        setupNavigation()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let { setContentView(it.root) }
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        binding?.apply {
            bottomMenu.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.user_list_fragment
                    || destination.id == R.id.album_fragment
                    || destination.id == R.id.photo_fragment
                    || destination.id == R.id.no_internet_error_fragment
                    || destination.id == R.id.server_error_fragment
                    || destination.id == R.id.unknown_error_fragment
                ) {
                    bottomMenu.visibility = View.VISIBLE
                } else {
                    bottomMenu.visibility = View.GONE
                }
            }
        }
    }

}