package com.antgut.myapplication.app.presentation.errors

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.antgut.myapplication.databinding.FragmentErrorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ErrorAppFragment() : Fragment() {

    private var binding: FragmentErrorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentErrorBinding.inflate(inflater)
        buttonNavigate()
        return binding?.root
    }

    protected fun setErrorTitle(title: String) {
        binding?.apply {
            labelTitleError.text = title
        }
    }

    protected fun setErrorDescription(description: String) {
        binding?.apply {
            labelDescError.text = description
        }
    }

    protected fun setImage(image: Drawable) {
        binding?.apply {
            imgError.setImageDrawable(image)
        }
    }

    private fun buttonNavigate() {
        binding?.apply {
            actionClose.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}