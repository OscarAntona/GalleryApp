package com.antgut.myapplication.features.album.data

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.album.data.local.AlbumLocalDataSource
import com.antgut.myapplication.features.album.data.local.cache.AlbumCache
import com.antgut.myapplication.features.album.data.remote.AlbumRemoteDataSource
import com.antgut.myapplication.features.album.domain.Album
import com.antgut.myapplication.features.album.domain.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class AlbumDataRepository @Inject constructor(
    private val remoteDataSource: AlbumRemoteDataSource,
    private val localDataSource: AlbumLocalDataSource,
    private val cache: AlbumCache
) : AlbumRepository {

    override suspend fun getAllAlbums(): Either<ErrorApp, Flow<List<Album>>> {
        return if (cache.outDated() || !hasLocalDataSourceAlbums(localDataSource.getAlbums())) {
            return remoteDataSource.getAlbums().map { remoteAlbums ->
                localDataSource.clear()
                localDataSource.saveAlbums(remoteAlbums)
                cache.saveDate()
                localDataSource.getAlbums()
            }
        } else {
            localDataSource.getAlbums().right()
        }
    }


    override suspend fun getAlbum(albumId: Int): Either<ErrorApp, Album> {
        val localAlbum = localDataSource.getAlbumById(albumId)
        return if (localAlbum.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localAlbum
        }
    }

    override suspend fun saveAlbum(album: Album) {
        localDataSource.saveAlbum(album)
    }

    private suspend fun hasLocalDataSourceAlbums(localAlbums: Flow<List<Album>>): Boolean {
        return localAlbums.firstOrNull()?.isNotEmpty() ?: false
    }

    override suspend fun getAlbumsByUser(userId: Int): Either<ErrorApp, Flow<List<Album>>> {

        return if (!hasLocalDataSourceAlbums(
                localDataSource.getAlbumsByUser(
                    userId
                )
            )
        ) {
            return remoteDataSource.getAlbumsByUser(userId).map { remoteAlbums ->
                localDataSource.saveAlbums(remoteAlbums)
                localDataSource.getAlbumsByUser(userId)
            }
        } else {
            localDataSource.getAlbumsByUser(userId).right()
        }
    }

    override suspend fun updateAlbum(album: Album): Either<ErrorApp, Boolean> {
        val localAlbum = localDataSource.updateAlbum(album)
        return if (localAlbum.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localAlbum
        }
    }

    override suspend fun deleteAlbum(albumId: Int): Either<ErrorApp, Boolean> {
        val localAlbum = localDataSource.deleteAlbum(albumId)
        return if (localAlbum.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localAlbum
        }
    }
}

