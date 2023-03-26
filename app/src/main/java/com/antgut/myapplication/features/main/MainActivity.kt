package com.antgut.myapplication.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antgut.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupBinding()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let { setContentView(it.root) }
    }

}