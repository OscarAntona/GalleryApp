package com.antgut.myapplication.features.user.data.local.db

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.user.data.local.ServerUserLocalDataSource
import com.antgut.myapplication.features.user.domain.User
import javax.inject.Inject

class ServerUserDbLocalDataSource @Inject constructor(private val dao: ServerUserDao) : ServerUserLocalDataSource {
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

    override suspend fun getUsers(): List<User> {
        val userLocal = dao.getAllUser()
        return if (userLocal.isEmpty()) {
            emptyList()
        } else {
            userLocal.map {
                it.toDomain()
            }
        }
    }

    override suspend fun deleteUser(id: Int): Either<ErrorApp, Boolean> {
        return try {
            dao.deleteUser(id)
            true.right()
        } catch (e: Exception) {
            ErrorApp.DataError.left()
        }
    }

    override suspend fun getUserById(id: Int): Either<ErrorApp, User> {
        dao.getUserById(id).apply {
            return this?.toDomain()?.right() ?: ErrorApp.DataError.left()
        }
    }

    override suspend fun clear() {
        dao.deleteAllUser()
    }
}