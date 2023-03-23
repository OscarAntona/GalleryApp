package com.antgut.myapplication.features.photo.di

import com.antgut.myapplication.features.photo.data.PhotoDataRepository
import com.antgut.myapplication.features.photo.data.local.PhotoLocalDataSource
import com.antgut.myapplication.features.photo.data.local.db.PhotoDbLocalDataSource
import com.antgut.myapplication.features.photo.data.remote.PhotoRemoteDataSource
import com.antgut.myapplication.features.photo.data.remote.api.PhotoApiRemoteDataSource
import com.antgut.myapplication.features.photo.domain.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PhotoModule {
    @Binds
    abstract fun bindPhotoRepository(repository: PhotoDataRepository): PhotoRepository

    @Binds
    abstract fun bindPhotoRemoteRepository(repository: PhotoApiRemoteDataSource): PhotoRemoteDataSource

    @Binds
    abstract fun bindPhotoLocalRepository(repository: PhotoDbLocalDataSource): PhotoLocalDataSource
}