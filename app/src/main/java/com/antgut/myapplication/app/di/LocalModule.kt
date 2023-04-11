package com.antgut.myapplication.app.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.antgut.myapplication.app.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "AppDatabase"
        ).build()
    }

    @Provides
    @Singleton
    @UserCacheQualifier
    fun provideUserCacheSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("User cache", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    @AlbumCacheQualifier
    fun provideAlbumCacheSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Album cache", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    @PhotoCacheQualifier
    fun providePhotoCacheSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Photo cache", Context.MODE_PRIVATE)
    }

}
