package com.antgut.myapplication.features.photo.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either

interface PhotoRepository {
    suspend fun getPhotos(): Either<ErrorApp, List<Photo>>
    suspend fun getPhoto(photoId: Int): Either<ErrorApp, Photo>
    suspend fun savePhoto(photo: Photo)
    suspend fun deletePhoto(photoId: Int): Either<ErrorApp, Boolean>
}