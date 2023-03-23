package com.antgut.myapplication.features.album.di

import com.antgut.myapplication.app.data.local.db.AppDatabase
import com.antgut.myapplication.features.album.data.local.db.AlbumDao
import com.antgut.myapplication.features.album.data.remote.api.AlbumApiEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlbumProvidesModule {
    @Provides
    @Singleton
    fun provideAlbumDao(database: AppDatabase): AlbumDao {
        return database.albumDao()
    }

    @Provides
    @Singleton
    fun providesAlbumService(retrofit: Retrofit): AlbumApiEndPoints =
        retrofit.create(AlbumApiEndPoints::class.java)
}