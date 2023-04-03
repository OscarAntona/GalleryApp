package com.antgut.myapplication.features.user.di

import com.antgut.myapplication.features.user.data.ServerUserDataRepository
import com.antgut.myapplication.features.user.data.local.LocalUserLocalDataSource
import com.antgut.myapplication.features.user.data.local.ServerUserLocalDataSource
import com.antgut.myapplication.features.user.data.local.db.LocalUserDbLocalDataSource
import com.antgut.myapplication.features.user.data.local.db.ServerUserDbLocalDataSource
import com.antgut.myapplication.features.user.data.remote.UserRemoteDataSource
import com.antgut.myapplication.features.user.data.remote.api.UserApiRemoteDataSource
import com.antgut.myapplication.features.user.domain.ServerUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserModule {
    @Binds
    abstract fun bindUserRepository(repository: ServerUserDataRepository): ServerUserRepository

    @Binds
    abstract fun bindUserRemoteRepository(repository: UserApiRemoteDataSource): UserRemoteDataSource

    @Binds
    abstract fun bindRemoteUserLocalRepository(repository: ServerUserDbLocalDataSource): ServerUserLocalDataSource

    @Binds
    abstract fun bindLocalUserLocalRepository(repository: LocalUserDbLocalDataSource): LocalUserLocalDataSource
}