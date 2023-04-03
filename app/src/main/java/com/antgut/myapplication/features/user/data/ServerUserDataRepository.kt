package com.antgut.myapplication.features.user.data

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.user.data.local.ServerUserLocalDataSource
import com.antgut.myapplication.features.user.data.remote.UserRemoteDataSource
import com.antgut.myapplication.features.user.domain.User
import com.antgut.myapplication.features.user.domain.ServerUserRepository


import javax.inject.Inject

class ServerUserDataRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val serverUserLocalDataSource: ServerUserLocalDataSource
) : ServerUserRepository {
    override suspend fun getServerUsers(): Either<ErrorApp, List<User>> {
        val localServerUsers = serverUserLocalDataSource.getUsers()
        return if (localServerUsers.isEmpty()) {
            return remoteDataSource.getUsers().map { remoteUsers ->
                serverUserLocalDataSource.clear()
                serverUserLocalDataSource.saveUsers(remoteUsers)
                remoteUsers
            }
        } else {
            localServerUsers.right()
        }
    }

    override suspend fun getServerUser(userId: Int): Either<ErrorApp, User> {
        val localServerUser = serverUserLocalDataSource.getUserById(userId)
        return if (localServerUser.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localServerUser
        }
    }

    override suspend fun saveServerUser(user: User) {
        serverUserLocalDataSource.saveUser(user)
    }

    override suspend fun updateServerUser(user: User): Either<ErrorApp, Boolean> {
        val localServerUser = serverUserLocalDataSource.updateUser(user)
        return if (localServerUser.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localServerUser
        }
    }

    override suspend fun deleteServerUser(userId: Int): Either<ErrorApp, Boolean> {
        val localServerUser = serverUserLocalDataSource.deleteUser(userId)
        return if (localServerUser.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localServerUser
        }
    }

}