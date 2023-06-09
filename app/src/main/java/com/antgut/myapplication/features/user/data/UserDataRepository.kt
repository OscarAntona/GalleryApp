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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
    private val cache: UserCache
) : UserRepository {

    override suspend fun getUsers(): Either<ErrorApp, Flow<List<User>>> {
        return if (cache.outDated() || !hasLocalDataSourceAlbums(localDataSource.getUsers())) {
            remoteDataSource.getUsers().map { remoteUsers ->
                localDataSource.clear()
                localDataSource.saveUsers(remoteUsers)
                cache.saveDate()
                localDataSource.getUsers()
            }
        } else {
            localDataSource.getUsers().right()
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

    private suspend fun hasLocalDataSourceAlbums(localAlbums: Flow<List<User>>): Boolean {
        return localAlbums.firstOrNull()?.isNotEmpty() ?: false
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