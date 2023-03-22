package com.antgut.myapplication.app.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Provides
@Singleton
fun provideUserSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
    return appContext.getSharedPreferences("user_xml", Context.MODE_PRIVATE)
}

@Provides
@Singleton
fun providePostSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
    return appContext.getSharedPreferences("post_xml", Context.MODE_PRIVATE)
}
