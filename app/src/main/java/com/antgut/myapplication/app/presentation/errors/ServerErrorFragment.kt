package com.antgut.myapplication.app.presentation.errors

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.antgut.myapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerErrorFragment : ErrorAppFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val errorDescription: String = getString(R.string.label_desc_server_error)
        val errorTitle: String = getString(R.string.label_title_server_error)
        val errorImage: Drawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.error_image)!!
        setErrorDescription(errorDescription)
        setErrorTitle(errorTitle)
        setImage(errorImage)
    }
}