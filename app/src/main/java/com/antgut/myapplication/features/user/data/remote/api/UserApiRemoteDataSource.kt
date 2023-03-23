package com.antgut.myapplication.features.user.data.remote.api

import com.antgut.myapplication.app.data.remote.apiCall
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.features.user.data.remote.UserRemoteDataSource
import com.antgut.myapplication.features.user.domain.User
import javax.inject.Inject

class UserApiRemoteDataSource @Inject constructor(val userApiEndPoints: UserApiEndPoints) :
    UserRemoteDataSource {

    override suspend fun getUsers(): Either<ErrorApp, List<User>> {
        return apiCall {
            userApiEndPoints.getUsers()
        }.map { it ->
            it.map { it.toDomain() }
        }
    }
}