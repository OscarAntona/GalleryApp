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

    override suspend fun getAlbum(albumId: Int): Either<ErrorApp, Album> {
        dao.getAlbumById(albumId).apply {
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