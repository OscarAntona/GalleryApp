package com.antgut.myapplication.features.user.data.local

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either

import com.antgut.myapplication.features.user.domain.User

interface ServerUserLocalDataSource {
    suspend fun saveUsers(user: List<User>)
    suspend fun saveUser(user: User)
    suspend fun getUsers(): List<User>
    suspend fun getUserById(userId: Int): Either<ErrorApp, User>
    suspend fun updateUser(user: User): Either<ErrorApp, Boolean>
    suspend fun deleteUser(userId: Int): Either<ErrorApp, Boolean>
    suspend fun clear()

}