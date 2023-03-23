package com.antgut.myapplication.features.album.data

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.album.data.local.AlbumLocalDataSource
import com.antgut.myapplication.features.album.data.remote.AlbumRemoteDataSource
import com.antgut.myapplication.features.album.domain.Album
import com.antgut.myapplication.features.album.domain.AlbumRepository
import javax.inject.Inject

class AlbumDataRepository @Inject constructor(
    private val remoteDataSource: AlbumRemoteDataSource,
    private val localDataSource: AlbumLocalDataSource
) : AlbumRepository {
    override suspend fun getAlbums(): Either<ErrorApp, List<Album>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbum(albumId: Int): Either<ErrorApp, Album> {
        TODO("Not yet implemented")
    }

    override suspend fun saveAlbum(album: Album) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAlbum(albumId: Int): Either<ErrorApp, Boolean> {
        TODO("Not yet implemented")
    }

}
