package com.antgut.myapplication.features.user.data.local.db

import androidx.room.*

@Dao
interface ServerUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(vararg user: ServerUserEntity)

    @Query("DELETE FROM $TABLE_REMOTE_USERS_NAME")
    fun deleteAllUser()

    @Query("DELETE FROM $TABLE_REMOTE_USERS_NAME WHERE $REMOTE_USER_KEY = :userId")
    suspend fun deleteUser(userId: Int)

    @Query("SELECT * FROM $TABLE_REMOTE_USERS_NAME WHERE $REMOTE_USER_KEY= :userId")
    suspend fun getUserById(userId: Int): ServerUserEntity?

    @Query("SELECT * FROM $TABLE_REMOTE_USERS_NAME")
    suspend fun getAllUser(): List<ServerUserEntity>
}