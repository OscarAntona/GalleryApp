package com.antgut.myapplication.features.user.di

import com.antgut.myapplication.features.user.data.UserDataRepository
import com.antgut.myapplication.features.user.data.local.UserLocalDataSource
import com.antgut.myapplication.features.user.data.local.db.UserDbLocalDataSource
import com.antgut.myapplication.features.user.data.remote.UserRemoteDataSource
import com.antgut.myapplication.features.user.data.remote.api.UserApiRemoteDataSource
import com.antgut.myapplication.features.user.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserModule {
    @Binds
    abstract fun bindUserRepository(repository: UserDataRepository): UserRepository

    @Binds
    abstract fun bindUserRemoteRepository(repository: UserApiRemoteDataSource): UserRemoteDataSource

    @Binds
    abstract fun bindUserLocalRepository(repository: UserDbLocalDataSource): UserLocalDataSource

}