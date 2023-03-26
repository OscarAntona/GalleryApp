package com.antgut.myapplication.features.album.data.remote.api

import com.antgut.myapplication.app.data.remote.apiCall
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.album.data.remote.AlbumRemoteDataSource
import com.antgut.myapplication.features.album.domain.Album
import javax.inject.Inject

class AlbumApiRemoteDataSource @Inject constructor(val albumApiEndPoints: AlbumApiEndPoints) :
    AlbumRemoteDataSource {

    override suspend fun getAlbums(userId:Int): Either<ErrorApp, List<Album>> {
        return apiCall {
            albumApiEndPoints.getAlbums(userId)
        }.map { it ->
            it.map { it.toDomain() }
        }
    }
}