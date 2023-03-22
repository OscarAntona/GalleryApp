package com.antgut.myapplication.features.user.di

import com.antgut.myapplication.features.user.data.local.db.UserDao
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
    fun provideUserDao(database: AppDatabase): UserDao {
        return AppDatabase.UserDao()
    }

    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit): UserApiEndPoints =
        retrofit.create(UserApiEndPoints::class.java)
}