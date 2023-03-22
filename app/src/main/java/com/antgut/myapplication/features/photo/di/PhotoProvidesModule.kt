package com.antgut.myapplication.features.photo.di

import com.antgut.myapplication.features.photo.data.local.db.PhotoDao
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
        return AppDatabase.PhotoDao()
    }

    @Singleton
    @Provides
    fun providesPhotoService(retrofit: Retrofit): PhotoApiEndPoints =
        retrofit.create(PhotoApiEndPoints::class.java)
}