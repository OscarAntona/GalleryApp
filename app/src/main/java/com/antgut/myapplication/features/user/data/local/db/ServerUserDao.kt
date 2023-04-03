package com.antgut.myapplication.features.user.data.local.db

import androidx.room.*

@Dao
interface ServerUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(vararg user: ServerUserEntity)

    @Query("DELETE FROM $TABLE_REMOTE_USERS_NAME")
    fun deleteAllUser()

    @Query("DELETE FROM $TABLE_REMOTE_USERS_NAME WHERE id = :id")
    suspend fun deleteUser(id: Int)

    @Query("SELECT * FROM $TABLE_REMOTE_USERS_NAME WHERE id = :id")
    suspend fun getUserById(id: Int): ServerUserEntity?

    @Query("SELECT * FROM $TABLE_REMOTE_USERS_NAME")
    suspend fun getAllUser(): List<ServerUserEntity>
}