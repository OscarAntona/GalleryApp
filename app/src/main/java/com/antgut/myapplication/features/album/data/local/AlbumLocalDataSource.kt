package com.antgut.myapplication.features.album.data.local

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.album.domain.Album

interface AlbumLocalDataSource {
    suspend fun saveAlbum(album: List<Album>)
    suspend fun getAlbums(): Either<ErrorApp, List<Album>>
    suspend fun getAlbum(albumId: Int): Either<ErrorApp, Album>
    suspend fun clear()

}