package com.antgut.myapplication.app.extensions

import android.app.Activity
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.antgut.myapplication.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(text: String) {
    Snackbar.make(
        this,
        text,
        Snackbar.LENGTH_LONG
    ).show()
}

fun Activity.navController(): NavController =
    Navigation.findNavController(this, R.id.fragment_container_view)