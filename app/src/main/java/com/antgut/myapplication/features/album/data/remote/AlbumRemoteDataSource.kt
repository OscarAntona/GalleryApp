package com.antgut.myapplication.features.album.data.remote

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.album.domain.Album

interface AlbumRemoteDataSource {
    suspend fun getAlbums(userId:Int): Either<ErrorApp, List<Album>>
}