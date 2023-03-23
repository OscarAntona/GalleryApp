package com.antgut.myapplication.app.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antgut.myapplication.BuildConfig
import com.antgut.myapplication.features.album.data.local.db.AlbumDao
import com.antgut.myapplication.features.album.data.local.db.AlbumEntity
import com.antgut.myapplication.features.photo.data.local.db.PhotoDao
import com.antgut.myapplication.features.photo.data.local.db.PhotoEntity
import com.antgut.myapplication.features.user.data.local.db.UserDao
import com.antgut.myapplication.features.user.data.local.db.UserEntity

@Database(
    entities = [PhotoEntity::class, AlbumEntity::class, UserEntity::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    abstract fun albumDao(): AlbumDao
    abstract fun userDao(): UserDao
}