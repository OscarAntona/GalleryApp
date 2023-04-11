package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either

interface UserRepository {
    suspend fun getUsers(): Either<ErrorApp, List<User>>
    suspend fun getUser(userId: Int): Either<ErrorApp, User>
    suspend fun updateUser(user: User): Either<ErrorApp, Boolean>
    suspend fun deleteUser(userId: Int): Either<ErrorApp, Boolean>
    suspend fun saveUser(user: User)
}