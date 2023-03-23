package com.antgut.myapplication.features.photo.data

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.photo.data.local.PhotoLocalDataSource
import com.antgut.myapplication.features.photo.data.remote.PhotoRemoteDataSource
import com.antgut.myapplication.features.photo.domain.Photo
import com.antgut.myapplication.features.photo.domain.PhotoRepository
import javax.inject.Inject

class PhotoDataRepository @Inject constructor(
    private val remoteDataSource: PhotoRemoteDataSource,
    private val localDataSource: PhotoLocalDataSource
):PhotoRepository{
    override suspend fun getPhotos(): Either<ErrorApp, List<Photo>> {
        val localPhotos = localDataSource.getPhotos()
        return if (localPhotos.isEmpty()) {
            return remoteDataSource.getPhotos().map { remotePhotos ->
                localDataSource.clear()
                localDataSource.savePhoto(remotePhotos)
                remotePhotos
            }
        } else {
            localPhotos.right()
        }
    }

    override suspend fun getPhoto(photoId: Int): Either<ErrorApp, Photo> {
        TODO("Not yet implemented")
    }

    override suspend fun savePhoto(photo: Photo) {
        TODO("Not yet implemented")
    }

    override suspend fun getPhotoByAlbum(albumId: Int): Either<ErrorApp, Photo> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePhoto(photo: Photo): Either<ErrorApp, Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePhoto(photoId: Int): Either<ErrorApp, Boolean> {
        TODO("Not yet implemented")
    }

}