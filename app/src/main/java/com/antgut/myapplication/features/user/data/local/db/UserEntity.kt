package com.antgut.myapplication.features.user.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME = "user"
const val PK_NAME = "id"

@Entity(tableName = TABLE_NAME)
data class UserEntity(
    @PrimaryKey @ColumnInfo(name = PK_NAME) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
)