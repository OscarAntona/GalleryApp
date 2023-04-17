package com.antgut.myapplication.features.photo.data.local.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePhoto(vararg photo: PhotoEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE serverId = 1")
    suspend fun deleteAllPhoto()

    @Query("DELETE FROM $TABLE_NAME WHERE $PK_NAME = :photoId")
    suspend fun deletePhoto(photoId: Int)

    @Query("SELECT * FROM $TABLE_NAME WHERE $ALBUM_KEY = :albumId")
    fun getPhotosByAlbum(albumId: Int): Flow<List<PhotoEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $PK_NAME = :photoId")
    suspend fun getPhotoById(photoId: Int): PhotoEntity?

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllPhoto(): Flow<List<PhotoEntity>>
}