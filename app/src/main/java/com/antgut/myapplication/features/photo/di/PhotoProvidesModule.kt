package com.antgut.myapplication.features.photo.di

import com.antgut.myapplication.app.data.local.db.AppDatabase
import com.antgut.myapplication.features.photo.data.local.db.PhotoDao
import com.antgut.myapplication.features.photo.data.remote.api.PhotoApiEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhotoProvidesModule {
    @Provides
    @Singleton
    fun providePhotoDao(database: AppDatabase): PhotoDao {
        return database.photoDao()
    }

    @Singleton
    @Provides
    fun providesPhotoService(retrofit: Retrofit): PhotoApiEndPoints =
        retrofit.create(PhotoApiEndPoints::class.java)
}