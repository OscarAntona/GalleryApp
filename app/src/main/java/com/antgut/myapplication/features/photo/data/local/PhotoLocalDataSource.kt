package com.antgut.myapplication.features.photo.data.local

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.photo.domain.Photo

interface PhotoLocalDataSource {
    suspend fun savePhoto(photo: List<Photo>)
    suspend fun getPhotos(): List<Photo>
    suspend fun getPhotoByAlbum(albumId: Int): Either<ErrorApp, Photo>
    suspend fun getPhotoById(photoId: Int): Either<ErrorApp,Photo>
    suspend fun updatePhoto(photo: Photo): Either<ErrorApp, Boolean>
    suspend fun deletePhoto(photoId: Int): Either<ErrorApp, Boolean>
    suspend fun clear()

}