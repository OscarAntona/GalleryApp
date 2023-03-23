package com.antgut.myapplication.features.album.data.local

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.album.domain.Album

interface AlbumLocalDataSource {
    suspend fun saveAlbums(album: List<Album>)
    suspend fun saveAlbum(album: Album)
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbumsByUser(userId: Int): Either<ErrorApp, List<Album>>
    suspend fun getAlbumById(albumId: Int): Either<ErrorApp, Album>
    suspend fun updateAlbum(album: Album): Either<ErrorApp, Boolean>
    suspend fun deleteAlbum(albumId: Int): Either<ErrorApp, Boolean>
    suspend fun clear()

}