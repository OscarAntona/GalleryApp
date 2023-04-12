package com.antgut.myapplication.features.user.data.local.db

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.user.data.local.UserLocalDataSource
import com.antgut.myapplication.features.user.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDbLocalDataSource @Inject constructor(private val dao: UserDao) : UserLocalDataSource {
    override suspend fun saveUsers(user: List<User>) {
        user.forEach {
            dao.saveUser(it.toEntity())
        }
    }

    override suspend fun saveUser(user: User) {
        dao.saveUser(user.toEntity())
    }

    override suspend fun updateUser(user: User): Either<ErrorApp, Boolean> {
        return user.id?.let {
            dao.getUserById(it)?.apply {
                dao.saveUser(user.toEntity())
            }?.let { true.right() }
        } ?: ErrorApp.DataError.left()
    }

    override suspend fun getUsers(): Flow<List<User>> = dao.getAllUser()
        .map { userLocal ->
            if (userLocal.isEmpty()) {
                emptyList()
            } else {
                userLocal.map {
                    it.toDomain()
                }
            }
        }


    override suspend fun deleteUser(userId: Int): Either<ErrorApp, Boolean> {
        return try {
            dao.deleteUser(userId)
            true.right()
        } catch (e: Exception) {
            ErrorApp.DataError.left()
        }
    }

    override suspend fun getUserById(userId: Int): Either<ErrorApp, User> {
        dao.getUserById(userId).apply {
            return this?.toDomain()?.right() ?: ErrorApp.DataError.left()
        }
    }

    override suspend fun clear() {
        dao.deleteAllUser()
    }
}