package com.antgut.myapplication.features.photo.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME = "photo"
const val PK_NAME = "id"
const val ALBUM_KEY = "albumId"

@Entity(tableName = TABLE_NAME)
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "serverId") val serverId:Int?,
    @ColumnInfo(name = "albumId") val albumId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "thumbnailUrl") val thumbnailUrl: String,
)