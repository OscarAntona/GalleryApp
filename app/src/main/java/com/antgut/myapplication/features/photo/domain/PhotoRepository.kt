package com.antgut.myapplication.features.photo.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.album.domain.Album

interface PhotoRepository {
    suspend fun getPhotos(): Either<ErrorApp, List<Photo>>
    suspend fun getPhoto(photoId: Int): Either<ErrorApp, Photo>
    suspend fun savePhoto(photo: Photo)
    suspend fun getPhotoByAlbum(albumId: Int): Either<ErrorApp, Photo>
    suspend fun updatePhoto(photo: Photo): Either<ErrorApp, Boolean>
    suspend fun deletePhoto(photoId: Int): Either<ErrorApp, Boolean>
}