package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either

interface UserRepository {
    suspend fun getUsers(): Either<ErrorApp, List<User>>
    suspend fun getUser(userId: Int): Either<ErrorApp, User>
    suspend fun saveServerUser(user: User)
    suspend fun updateServerUser(user: User): Either<ErrorApp, Boolean>
    suspend fun deleteServerUser(userId: Int): Either<ErrorApp, Boolean>

}