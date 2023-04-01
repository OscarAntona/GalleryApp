package com.antgut.myapplication.features.user.data.local.db

import androidx.room.*

@Dao
interface LocalUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLocalUser(vararg user: LocalUserEntity)

    @Query("DELETE FROM $TABLE_LOCAL_USERS_NAME WHERE id = :userId")
    suspend fun deleteLocalUser(userId: Int)

    @Query("SELECT * FROM $TABLE_LOCAL_USERS_NAME WHERE id = :userId")
    suspend fun getLocalUserById(userId: Int): LocalUserEntity?

    @Query("SELECT * FROM $TABLE_LOCAL_USERS_NAME")
    suspend fun getAllLocalUser(): List<LocalUserEntity>
}