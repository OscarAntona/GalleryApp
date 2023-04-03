package com.antgut.myapplication.features.user.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_LOCAL_USERS_NAME = "local_users"

@Entity(tableName = TABLE_LOCAL_USERS_NAME)
data class LocalUserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
)

