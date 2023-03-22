package com.antgut.myapplication.features.album.di

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
        return AppDatabase.AlbumDao()
    }

    @Provides
    @Singleton
    fun providesAlbumService(retrofit: Retrofit): AlbumApiEndPoints =
        retrofit.create(AlbumApiEndPoints::class.java)
}