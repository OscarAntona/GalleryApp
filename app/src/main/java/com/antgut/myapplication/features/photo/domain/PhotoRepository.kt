package com.antgut.myapplication.features.photo.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhotosByAlbum(albumId: Int): Either<ErrorApp, Flow<List<Photo>>>
    suspend fun getAllPhotos(): Either<ErrorApp, Flow<List<Photo>>>
    suspend fun getPhoto(photoId: Int): Either<ErrorApp, Photo>
    suspend fun savePhoto(photo: Photo)
    suspend fun updatePhoto(photo: Photo): Either<ErrorApp, Boolean>
    suspend fun deletePhoto(photoId: Int): Either<ErrorApp, Boolean>
}