package com.antgut.myapplication.app.extensions

import android.widget.ImageView
import com.antgut.myapplication.R
import com.bumptech.glide.Glide

fun ImageView.loadUrl(urlImage: String) {
    Glide.with(this).load(urlImage).error(R.drawable.ic_launcher_background).into(this)
}
