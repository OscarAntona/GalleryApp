package com.antgut.myapplication.features.photo.data.local

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.photo.domain.Photo

interface PhotoLocalDataSource {
    suspend fun savePhoto(photo: List<Photo>)
    suspend fun getPhotos(): Either<ErrorApp, List<Photo>>
    suspend fun getPhoto(photoId: Int): Either<ErrorApp, Photo>
    suspend fun clear()

}