package com.antgut.myapplication.features.photo.data.local.db

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.photo.data.local.PhotoLocalDataSource
import com.antgut.myapplication.features.photo.domain.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoDbLocalDataSource @Inject constructor(
    private val dao: PhotoDao
) : PhotoLocalDataSource {
    override suspend fun savePhotos(photo: List<Photo>) {
        photo.forEach {
            dao.savePhoto(it.toEntity())
        }
    }

    override suspend fun savePhoto(photo: Photo) {
        dao.savePhoto(photo.toEntity())
    }

    override suspend fun updatePhoto(photo: Photo): Either<ErrorApp, Boolean> {
        return photo.id?.let {
            dao.getPhotoById(it)?.apply {
                dao.savePhoto(photo.toEntity())
            }?.let { true.right() }
        } ?: ErrorApp.DataError.left()
    }

    override suspend fun deletePhoto(photoId: Int): Either<ErrorApp, Boolean> {
        return try {
            dao.deletePhoto(photoId)
            true.right()
        } catch (e: Exception) {
            ErrorApp.DataError.left()
        }
    }

    override suspend fun getPhotos(): Flow<List<Photo>> = dao.getAllPhoto()
        .map { photoLocal ->
            if (photoLocal.isEmpty()) {
                emptyList()
            } else {
                photoLocal.map {
                    it.toDomain()
                }
            }
        }

    override suspend fun getPhotosByAlbum(albumId: Int): Flow<List<Photo>> =
        dao.getPhotosByAlbum(albumId)
            .map { photoLocal ->
                if (photoLocal.isEmpty()) {
                    emptyList()
                } else {
                    photoLocal.map {
                        it.toDomain()
                    }
                }
            }

    override suspend fun getPhotoById(photoId: Int): Either<ErrorApp, Photo> {
        dao.getPhotoById(photoId).apply {
            return this?.toDomain()?.right() ?: ErrorApp.DataError.left()
        }
    }

    override suspend fun clear() {
        dao.deleteAllPhoto()
    }
}