package com.antgut.myapplication.features.user.data.local.db

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun saveUser(vararg user: UserEntity)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAllUser()

    @Query("SELECT * FROM $TABLE_NAME WHERE $PK_NAME= :userId")
    suspend fun getUserById(userId: Int): UserEntity?

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllUser(): List<UserEntity>
}