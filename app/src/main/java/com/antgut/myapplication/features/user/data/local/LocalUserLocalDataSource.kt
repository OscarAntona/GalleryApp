package com.antgut.myapplication.features.user.data.local

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either

import com.antgut.myapplication.features.user.domain.User

interface LocalUserLocalDataSource {
    suspend fun saveLocalUsers(user: List<User>)
    suspend fun saveLocalUser(user: User)
    suspend fun getLocalUsers(): List<User>
    suspend fun getLocalUserById(userId: Int): Either<ErrorApp, User>
    suspend fun updateLocalUser(user: User): Either<ErrorApp, Boolean>
    suspend fun deleteLocalUser(userId: Int): Either<ErrorApp, Boolean>
}
