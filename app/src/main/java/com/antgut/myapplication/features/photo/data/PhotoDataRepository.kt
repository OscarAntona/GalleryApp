package com.antgut.myapplication.features.photo.data

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.photo.data.local.PhotoLocalDataSource
import com.antgut.myapplication.features.photo.data.local.cache.PhotoCache
import com.antgut.myapplication.features.photo.data.remote.PhotoRemoteDataSource
import com.antgut.myapplication.features.photo.domain.Photo
import com.antgut.myapplication.features.photo.domain.PhotoRepository
import javax.inject.Inject

class PhotoDataRepository @Inject constructor(
    private val remoteDataSource: PhotoRemoteDataSource,
    private val localDataSource: PhotoLocalDataSource,
    private val cache: PhotoCache
) : PhotoRepository {
    override suspend fun getPhotosByAlbum(albumId: Int): Either<ErrorApp, List<Photo>> {
        val localPhotos = localDataSource.getPhotosByAlbum(albumId)
        return if (localPhotos.isLeft()) {
            return remoteDataSource.getPhotos().map { remotePhotos ->
                localDataSource.clear()
                localDataSource.savePhotos(remotePhotos)
                remotePhotos
            }
        } else {
            localPhotos
        }
    }

    override suspend fun getAllPhotos(): Either<ErrorApp, List<Photo>> {

        return if (cache.isCacheOutDated()) {
            return remoteDataSource.getPhotos().map { remotePhotos ->
                localDataSource.clear()
                localDataSource.savePhotos(remotePhotos)
                cache.saveCacheDate()
                remotePhotos
            }
        } else {
            localDataSource.getPhotos().right()
        }
    }

    override suspend fun getPhoto(photoId: Int): Either<ErrorApp, Photo> {
        val localAlbum = localDataSource.getPhotoById(photoId)
        return if (localAlbum.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localAlbum
        }
    }

    override suspend fun savePhoto(photo: Photo) {
        localDataSource.savePhoto(photo)
    }

    override suspend fun updatePhoto(photo: Photo): Either<ErrorApp, Boolean> {
        val localPhoto = localDataSource.updatePhoto(photo)
        return if (localPhoto.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localPhoto
        }
    }

    override suspend fun deletePhoto(photoId: Int): Either<ErrorApp, Boolean> {
        val localPhoto = localDataSource.deletePhoto(photoId)
        return if (localPhoto.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localPhoto
        }
    }

}