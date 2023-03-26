package com.antgut.myapplication.features.photo.data.remote.api

import com.antgut.myapplication.app.data.remote.apiCall
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.photo.data.remote.PhotoRemoteDataSource
import com.antgut.myapplication.features.photo.domain.Photo
import javax.inject.Inject

class PhotoApiRemoteDataSource @Inject constructor(val photoApiEndPoints: PhotoApiEndPoints) :
    PhotoRemoteDataSource {

    override suspend fun getPhotos(albumId: Int): Either<ErrorApp, List<Photo>> {
        return apiCall {
            photoApiEndPoints.getPhotos(albumId)
        }.map { it ->
            it.map { it.toDomain() }
        }
    }
}