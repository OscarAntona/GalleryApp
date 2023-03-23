package com.antgut.myapplication.features.user.data.remote

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.user.domain.User

interface UserRemoteDataSource {
    suspend fun getUsers(): Either<ErrorApp, List<User>>
}