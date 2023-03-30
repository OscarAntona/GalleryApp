package com.antgut.myapplication.app.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(text: String) {
    Snackbar.make(
        this,
        text,
        Snackbar.LENGTH_LONG
    ).show()
}
