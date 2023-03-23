package com.antgut.myapplication.features.album.data.local.db

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.album.data.local.AlbumLocalDataSource
import com.antgut.myapplication.features.album.domain.Album
import javax.inject.Inject

class AlbumDbLocalDataSource @Inject constructor(
    private val dao: AlbumDao
) : AlbumLocalDataSource {
    override suspend fun saveAlbum(album: List<Album>) {
        album.forEach { album ->
            dao.saveAlbum(album.toEntity())
        }
    }

    override suspend fun updateAlbum(album: Album): Either<ErrorApp, Boolean> {
        return dao.getAlbumById(album.id)?.apply {
            dao.saveAlbum(album.toEntity())
        }?.let { true.right() } ?: ErrorApp.DataError.left()
    }

    override suspend fun getAlbums(): Either<ErrorApp, List<Album>> {
        dao.getAllAlbum().apply {
            return if (this.isEmpty()) {
                ErrorApp.DataError.left()
            } else {
                this.map {
                    it.toDomain()
                }.right()
            }
        }
    }

    override suspend fun getAlbumById(albumId: Int): Either<ErrorApp, Album> {
        dao.getAlbumById(albumId).apply {
            return this?.toDomain()?.right() ?: ErrorApp.DataError.left()
        }
    }

    override suspend fun getAlbumByUser(userId: Int): Either<ErrorApp, Album> {
        dao.getAlbumByUserId(userId).apply {
            return if (this == null) {
                ErrorApp.DataError.left()
            } else {
                this.toDomain().right()
            }
        }
    }

    override suspend fun clear() {
        dao.deleteAllAlbum()
    }
}