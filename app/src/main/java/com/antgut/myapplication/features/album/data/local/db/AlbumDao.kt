package com.antgut.myapplication.features.album.data.local.db

import androidx.room.*

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun saveAlbum(vararg album: AlbumEntity)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAllAlbum()

    @Query("SELECT * FROM $TABLE_NAME WHERE $PK_NAME = :albumId")
    suspend fun getAlbumById(albumId: Int): AlbumEntity?

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllAlbum(): List<AlbumEntity>
}