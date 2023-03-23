package com.antgut.myapplication.features.album.data.local

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.album.domain.Album

interface AlbumLocalDataSource {
    suspend fun saveAlbum(album: List<Album>)
    suspend fun getAlbums(): Either<ErrorApp, List<Album>>
    suspend fun getAlbumByUser(userId: Int): Either<ErrorApp, Album>
    suspend fun getAlbumById(albumId: Int): Either<ErrorApp, Album>
    suspend fun updateAlbum(album: Album): Either<ErrorApp, Boolean>
    suspend fun clear()

}