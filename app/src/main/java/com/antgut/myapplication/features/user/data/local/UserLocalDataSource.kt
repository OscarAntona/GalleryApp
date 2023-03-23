package com.antgut.myapplication.features.user.data.local

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either

import com.antgut.myapplication.features.user.domain.User

interface UserLocalDataSource {
    suspend fun saveUser(user: List<User>)
    suspend fun getUsers(): Either<ErrorApp, List<User>>
    suspend fun getUser(userId: Int): Either<ErrorApp, User>
    suspend fun clear()

}