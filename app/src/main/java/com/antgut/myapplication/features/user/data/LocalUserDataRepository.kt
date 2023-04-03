package com.antgut.myapplication.features.user.data

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.features.user.data.local.LocalUserLocalDataSource
import com.antgut.myapplication.features.user.domain.LocalUserRepository
import com.antgut.myapplication.features.user.domain.User
import javax.inject.Inject

class LocalUserDataRepository @Inject constructor(
    private val localUserLocalDataSource: LocalUserLocalDataSource
): LocalUserRepository {
    override suspend fun getLocalUsers(): List<User> {
        val localUsers = localUserLocalDataSource.getLocalUsers()
        return localUsers.ifEmpty {
            emptyList()
        }
}

    override suspend fun getLocalUser(userId: Int): Either<ErrorApp, User> {
        val localUser = localUserLocalDataSource.getLocalUserById(userId)
        return if (localUser.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localUser
        }
    }

    override suspend fun saveLocalUser(user: User) {
        localUserLocalDataSource.saveLocalUser(user)
    }

    override suspend fun updateLocalUser(user: User): Either<ErrorApp, Boolean> {
       val localUser = localUserLocalDataSource.updateLocalUser(user)
        return if (localUser.isLeft()){
            ErrorApp.DataError.left()
        }else{
            localUser
        }
    }

    override suspend fun deleteLocalUser(userId: Int): Either<ErrorApp, Boolean> {
        val localUser = localUserLocalDataSource.deleteLocalUser(userId)
        return if(localUser.isLeft()){
            ErrorApp.DataError.left()
        }else{
            localUser
        }
    }
}