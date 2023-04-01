package com.antgut.myapplication.features.user.data.local.db

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.user.data.local.LocalUserLocalDataSource
import com.antgut.myapplication.features.user.domain.User
import javax.inject.Inject

class LocalUserDbLocalDataSource @Inject constructor(private val dao: LocalUserDao) :
    LocalUserLocalDataSource {
    override suspend fun saveLocalUsers(user: List<User>) {
        user.forEach {
            dao.saveLocalUser(it.toLocalEntity())
        }
    }

    override suspend fun saveLocalUser(user: User) {
        dao.saveLocalUser(user.toLocalEntity())
    }

    override suspend fun updateLocalUser(user: User): Either<ErrorApp, Boolean> {
        return user.id?.let {
            dao.getLocalUserById(it)?.apply {
                dao.saveLocalUser(user.toLocalEntity())
            }?.let { true.right() }
        } ?: ErrorApp.DataError.left()
    }

    override suspend fun getLocalUsers(): List<User> {
        val userLocal = dao.getAllLocalUser()
        return if (userLocal.isEmpty()) {
            emptyList()
        } else {
            userLocal.map {
                it.toDomain()
            }
        }
    }

    override suspend fun deleteLocalUser(userId: Int): Either<ErrorApp, Boolean> {
        return try {
            dao.deleteLocalUser(userId)
            true.right()
        } catch (e: Exception) {
            ErrorApp.DataError.left()
        }
    }

    override suspend fun getLocalUserById(userId: Int): Either<ErrorApp, User> {
        dao.getLocalUserById(userId).apply {
            return this?.toDomain()?.right() ?: ErrorApp.DataError.left()
        }
    }
}