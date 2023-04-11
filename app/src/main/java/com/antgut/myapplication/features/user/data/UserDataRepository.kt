package com.antgut.myapplication.features.user.data

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.user.data.local.UserLocalDataSource
import com.antgut.myapplication.features.user.data.local.cache.UserCache
import com.antgut.myapplication.features.user.data.remote.UserRemoteDataSource
import com.antgut.myapplication.features.user.domain.User
import com.antgut.myapplication.features.user.domain.UserRepository
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
    private val cache: UserCache
) : UserRepository {
    override suspend fun getUsers(): Either<ErrorApp, List<User>> {
        val localUsers = localDataSource.getUsers()
        return if (cache.isCacheOutDated()) {
            return remoteDataSource.getUsers().map { remoteUsers ->
                localDataSource.clear()
                localDataSource.saveUsers(remoteUsers)
                cache.saveCacheDate()
                remoteUsers
            }
        } else {
            localUsers.right()
        }
    }

    override suspend fun getUser(userId: Int): Either<ErrorApp, User> {
        val localUser = localDataSource.getUserById(userId)
        return if (localUser.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localUser
        }
    }

    override suspend fun saveUser(user: User) {
        localDataSource.saveUser(user)
    }

    override suspend fun updateUser(user: User): Either<ErrorApp, Boolean> {
        val localUser = localDataSource.updateUser(user)
        return if (localUser.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localUser
        }
    }

    override suspend fun deleteUser(userId: Int): Either<ErrorApp, Boolean> {
        val localUser = localDataSource.deleteUser(userId)
        return if (localUser.isLeft()) {
            ErrorApp.DataError.left()
        } else {
            localUser
        }
    }

}