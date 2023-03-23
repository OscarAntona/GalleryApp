package com.antgut.myapplication.features.photo.data.local.db

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.photo.data.local.PhotoLocalDataSource
import com.antgut.myapplication.features.photo.domain.Photo
import javax.inject.Inject

class PhotoDbLocalDataSource @Inject constructor(
    private val dao: PhotoDao
) : PhotoLocalDataSource {
    override suspend fun savePhoto(photo: List<Photo>) {
        photo.forEach { photo ->
            dao.savePhoto(photo.toEntity())
        }
    }

    override suspend fun updatePhoto(photo: Photo): Either<ErrorApp, Boolean> {
        return dao.getPhotoById(photo.id)?.apply {
            dao.savePhoto(photo.toEntity())
        }?.let { true.right() } ?: ErrorApp.DataError.left()
    }

    override suspend fun getPhotos(): Either<ErrorApp, List<Photo>> {
        dao.getAllPhoto().apply {
            return if (this.isEmpty()) {
                ErrorApp.DataError.left()
            } else {
                this.map {
                    it.toDomain()
                }.right()
            }
        }
    }

    override suspend fun getPhotoByAlbum(albumId: Int): Either<ErrorApp, Photo> {
        dao.getPhotoByAlbum(albumId).apply {
            return if (this == null) {
                ErrorApp.DataError.left()
            } else {
                this.toDomain().right()
            }
        }
    }

    override suspend fun clear() {
        dao.deleteAllPhoto()
    }
}