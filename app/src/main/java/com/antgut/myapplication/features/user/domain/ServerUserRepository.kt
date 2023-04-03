package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either

interface ServerUserRepository {
    suspend fun getServerUsers(): Either<ErrorApp, List<User>>
    suspend fun getServerUser(id: Int): Either<ErrorApp, User>
    suspend fun saveServerUser(user: User)
    suspend fun updateServerUser(user: User): Either<ErrorApp, Boolean>
    suspend fun deleteServerUser(id: Int): Either<ErrorApp, Boolean>
}