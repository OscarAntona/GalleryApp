package com.antgut.myapplication.features.photo.data.remote

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.photo.domain.Photo

interface PhotoRemoteDataSource {
    suspend fun getPhotos(albumId: Int): Either<ErrorApp, List<Photo>>
}