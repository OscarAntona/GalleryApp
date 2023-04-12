package com.antgut.myapplication.app.presentation.errors

import android.content.Context
import com.antgut.myapplication.NavGraphDirections
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.extensions.navController
import com.antgut.myapplication.features.main.MainActivity
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ErrorAppHandler @Inject constructor(@ActivityContext private val context: Context) {

    fun navigateToError(errorApp: ErrorApp) {
        when (errorApp) {
            is ErrorApp.DataError -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.actionToDataError())
            is ErrorApp.NoInternetError -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.actionToNointernetError())
            is ErrorApp.TimeOutError -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.actionToServerError())
            else -> (context as MainActivity).navController()
                .navigate(NavGraphDirections.actionToUnknownError())
        }
    }
}