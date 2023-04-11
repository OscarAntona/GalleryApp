package com.antgut.myapplication.features.album.data.local.cache

import android.content.SharedPreferences
import com.antgut.myapplication.app.di.PhotoCacheQualifier
import javax.inject.Inject

class AlbumCache @Inject constructor(
    @PhotoCacheQualifier private val preferences: SharedPreferences
) {

    private val editor = preferences.edit()

    fun isCacheOutDated(): Boolean {
        val timeSave = preferences.getLong("album cache", 1)
        return if (timeSave.compareTo(1) == 0) {
            true
        } else {
            val timeLimit = 1000 * 3600 * 2//horas
            val timePassed = (System.currentTimeMillis() - timeSave)
            timePassed > timeLimit
        }
    }

    fun saveCacheDate() {
        editor.putLong("album cache", System.currentTimeMillis())
        editor.apply()
    }
}