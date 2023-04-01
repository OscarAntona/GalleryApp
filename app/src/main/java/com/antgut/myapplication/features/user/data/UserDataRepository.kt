package com.antgut.myapplication.features.user.data

import android.util.Log
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.extensions.showSnackBar
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.user.data.local.LocalUserLocalDataSource
import com.antgut.myapplication.features.user.data.local.ServerUserLocalDataSource
import com.antgut.myapplication.features.user.data.remote.UserRemoteDataSource
import com.antgut.myapplication.features.user.domain.User
import com.antgut.myapplication.features.user.domain.UserRepository
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val serverUserLocalDataSource: ServerUserLocalDataSource,
    private val localUserLocalDataSource: LocalUserLocalDataSource
) : UserRepository {
    override suspend fun getUsers(): Either<ErrorApp, List<User>> {
        val localServerUsers = serverUserLocalDataSource.getUsers()
        val localUsers = localUserLocalDataSource.getLocalUsers()
        return if (localServerUsers.isEmpty()) {
            return remoteDataSource.getUsers().map { remoteUsers ->
                serverUserLocalDataSource.clear()
                serverUserLocalDataSource.saveUsers(remoteUsers)
                remoteUsers.plus(localUsers)
            }
        } else {
            localServerUsers.plus(localUsers).right()
        }
    }

    override suspend fun getUser(userId: Int): Either<ErrorApp, User> {
        val localServerUser = serverUserLocalDataSource.getUserById(userId)
        val localUser = localUserLocalDataSource.getLocalUserById(userId)

    }

    override suspend fun saveServerUser(user: User) {
        serverUserLocalDataSource.saveUser(user)
    }

    override suspend fun updateServerUser(user: User): Either<ErrorApp, Boolean> {
        val localUser = serverUserLocalDataSource.updateUser(user)
        return if (localUser.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localUser
        }
    }

    override suspend fun deleteServerUser(userId: Int): Either<ErrorApp, Boolean> {
        val localUser = serverUserLocalDataSource.deleteUser(userId)
        return if (localUser.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localUser
        }
    }
}