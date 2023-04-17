package com.antgut.myapplication.features.album.data.local.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAlbum(vararg album: AlbumEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE serverId IS NOT NULL")
    suspend fun deleteAllAlbum()

    @Query("SELECT * FROM $TABLE_NAME WHERE userId = :userId")
    fun getAlbumsByUser(userId: Int): Flow<List<AlbumEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :albumId")
    suspend fun getAlbumById(albumId: Int): AlbumEntity?

    @Query("DELETE FROM $TABLE_NAME WHERE id = :albumId")
    suspend fun deleteAlbum(albumId: Int)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllAlbum(): Flow<List<AlbumEntity>>
}