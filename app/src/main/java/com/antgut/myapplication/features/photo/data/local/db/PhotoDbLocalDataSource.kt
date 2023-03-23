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
        photo.forEach {
            dao.savePhoto(it.toEntity())
        }
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

    override suspend fun getPhoto(photoId: Int): Either<ErrorApp, Photo> {
        dao.getPhotoById(photoId).apply {
            return this?.toDomain()?.right() ?: ErrorApp.DataError.left()
        }
    }

    override suspend fun clear() {
        dao.deleteAllPhoto()
    }
}