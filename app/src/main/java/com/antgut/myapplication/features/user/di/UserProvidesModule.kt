package com.antgut.myapplication.features.user.di

import com.antgut.myapplication.app.data.local.db.AppDatabase
import com.antgut.myapplication.features.user.data.local.db.ServerUserDao
import com.antgut.myapplication.features.user.data.remote.api.UserApiEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserProvidesModule {
    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): ServerUserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit): UserApiEndPoints =
        retrofit.create(UserApiEndPoints::class.java)
}