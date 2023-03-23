package com.antgut.myapplication.features.photo.data.local.db

import androidx.room.*

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun savePhoto(vararg photo: PhotoEntity)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAllPhoto()

    @Query("SELECT * FROM $TABLE_NAME WHERE $PK_NAME = :photoId")
    suspend fun getPhotoById(photoId: Int): PhotoEntity?

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllPhoto(): List<PhotoEntity>
}