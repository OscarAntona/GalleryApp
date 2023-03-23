package com.antgut.myapplication.features.album.di

import com.antgut.myapplication.features.album.data.AlbumDataRepository
import com.antgut.myapplication.features.album.data.local.AlbumLocalDataSource
import com.antgut.myapplication.features.album.data.local.db.AlbumDbLocalDataSource
import com.antgut.myapplication.features.album.data.remote.AlbumRemoteDataSource
import com.antgut.myapplication.features.album.data.remote.api.AlbumApiRemoteDataSource
import com.antgut.myapplication.features.album.domain.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AlbumModule {
    @Binds
    abstract fun bindAlbumRepository(repository: AlbumDataRepository): AlbumRepository

    @Binds
    abstract fun bindAlbumRemoteRepository(repository: AlbumApiRemoteDataSource): AlbumRemoteDataSource

    @Binds
    abstract fun bindAlbumLocalRepository(repository: AlbumDbLocalDataSource): AlbumLocalDataSource
}