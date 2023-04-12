package com.antgut.myapplication.features.album.data.local

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.album.domain.Album
import kotlinx.coroutines.flow.Flow

interface AlbumLocalDataSource {
    suspend fun saveAlbums(album: List<Album>)
    suspend fun saveAlbum(album: Album)
    suspend fun getAlbums(): Flow<List<Album>>
    suspend fun getAlbumsByUser(userId: Int): Flow<List<Album>>
    suspend fun getAlbumById(albumId: Int): Either<ErrorApp, Album>
    suspend fun updateAlbum(album: Album): Either<ErrorApp, Boolean>
    suspend fun deleteAlbum(albumId: Int): Either<ErrorApp, Boolean>
    suspend fun clear()

}