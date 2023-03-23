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
    override suspend fun saveAlbums(album: List<Album>) {
        album.forEach {
            dao.saveAlbum(it.toEntity())
        }
    }

    override suspend fun saveAlbum(album: Album) {
        dao.saveAlbum(album.toEntity())
    }

    override suspend fun updateAlbum(album: Album): Either<ErrorApp, Boolean> {
        return dao.getAlbumById(album.id)?.apply {
            dao.saveAlbum(album.toEntity())
        }?.let { true.right() } ?: ErrorApp.DataError.left()
    }

    override suspend fun deleteAlbum(albumId: Int): Either<ErrorApp, Boolean> {
        return try {
            dao.deleteAlbum(albumId)
            true.right()
        } catch (e: Exception) {
            ErrorApp.DataError.left()
        }
    }

    override suspend fun getAlbums(): List<Album> {
        val albumLocal = dao.getAllAlbum()
        return if (albumLocal.isEmpty()) {
            emptyList()
        } else {
            albumLocal.map {
                it.toDomain()
            }
        }
    }

    override suspend fun getAlbumById(albumId: Int): Either<ErrorApp, Album> {
        dao.getAlbumById(albumId).apply {
            return this?.toDomain()?.right() ?: ErrorApp.DataError.left()
        }
    }

    override suspend fun getAlbumsByUser(userId: Int): Either<ErrorApp, List<Album>> {
        dao.getAlbumsByUser(userId).apply {
            return if (this.isEmpty()) {
                ErrorApp.DataError.left()
            } else {
                this.map {
                    it.toDomain()
                }.right()
            }
        }
    }

    override suspend fun clear() {
        dao.deleteAllAlbum()
    }
}