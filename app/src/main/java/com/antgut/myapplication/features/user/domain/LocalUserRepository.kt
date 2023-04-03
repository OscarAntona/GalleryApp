package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either

interface LocalUserRepository {
    suspend fun getLocalUsers():List<User>
    suspend fun getLocalUser(userId: Int): Either<ErrorApp, User>
    suspend fun saveLocalUser(user: User)
    suspend fun updateLocalUser(user: User): Either<ErrorApp, Boolean>
    suspend fun deleteLocalUser(userId: Int): Either<ErrorApp, Boolean>
}
