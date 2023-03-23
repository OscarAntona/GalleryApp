package com.antgut.myapplication.features.album.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME = "album"
const val PK_NAME = "id"

@Entity(tableName = TABLE_NAME)
data class AlbumEntity(
    @PrimaryKey @ColumnInfo(name = PK_NAME) val id: Int,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "title") val title: String,
)