package com.antgut.myapplication.features.photo.data.local.cache

import android.content.SharedPreferences
import com.antgut.myapplication.app.di.PhotoCacheQualifier
import javax.inject.Inject

class PhotoCache @Inject constructor(
    @PhotoCacheQualifier private val preferences: SharedPreferences
) {

    private val editor = preferences.edit()

    fun outDated(): Boolean {
        val timeSave = preferences.getLong("photo cache", 1)
        return if (timeSave.compareTo(1) == 0) {
            true
        } else {
            val timePassed = (System.currentTimeMillis() - timeSave)
            val timeLimit = 1000 * 3600 * 1//horas
            timePassed > timeLimit
        }
    }

    fun saveDate() {
        editor.putLong("photo cache", System.currentTimeMillis())
        editor.apply()
    }
}