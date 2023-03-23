package com.antgut.myapplication.features.album.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either

interface AlbumRepository {
    suspend fun getAlbums(): Either<ErrorApp, List<Album>>
    suspend fun getAlbum(albumId: Int): Either<ErrorApp, Album>
    suspend fun saveAlbum(album: Album)
    suspend fun getAlbumByUser(userId: Int): Either<ErrorApp, Album>
    suspend fun updateAlbum(album: Album): Either<ErrorApp, Boolean>
    suspend fun deleteAlbum(albumId: Int): Either<ErrorApp, Boolean>
}