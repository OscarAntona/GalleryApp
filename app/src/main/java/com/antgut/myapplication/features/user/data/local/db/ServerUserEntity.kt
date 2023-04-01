package com.antgut.myapplication.features.user.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_REMOTE_USERS_NAME = "remote_users"
const val REMOTE_USER_KEY = "serverId"

@Entity(tableName = TABLE_REMOTE_USERS_NAME)
data class ServerUserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = REMOTE_USER_KEY) val serverId: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
)

