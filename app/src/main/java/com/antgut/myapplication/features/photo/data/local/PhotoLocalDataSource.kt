package com.antgut.myapplication.features.photo.data.local

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.photo.domain.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoLocalDataSource {
    suspend fun savePhotos(photo: List<Photo>)
    suspend fun savePhoto(photo: Photo)
    suspend fun getPhotos(): Flow<List<Photo>>
    suspend fun getPhotosByAlbum(albumId: Int): Flow<List<Photo>>
    suspend fun getPhotoById(photoId: Int): Either<ErrorApp, Photo>
    suspend fun updatePhoto(photo: Photo): Either<ErrorApp, Boolean>
    suspend fun deletePhoto(photoId: Int): Either<ErrorApp, Boolean>
    suspend fun clear()

}