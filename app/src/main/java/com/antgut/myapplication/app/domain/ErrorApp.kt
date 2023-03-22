package com.antgut.myapplication.app.domain

sealed class ErrorApp {
    object DataError : ErrorApp()
    object NoInternetError : ErrorApp()
    object TimeOutError : ErrorApp()
    object UnKnowError : ErrorApp()
}