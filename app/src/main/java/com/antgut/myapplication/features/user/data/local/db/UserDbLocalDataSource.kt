package com.antgut.myapplication.features.user.data.local.db

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import com.antgut.myapplication.app.funcional.left
import com.antgut.myapplication.app.funcional.right
import com.antgut.myapplication.features.user.data.local.UserLocalDataSource
import com.antgut.myapplication.features.user.domain.User
import javax.inject.Inject

class UserDbLocalDataSource @Inject constructor(private val dao: UserDao) : UserLocalDataSource {
    override suspend fun saveUser(user: List<User>) {
        user.forEach { user ->
            dao.saveUser(user.toEntity())
        }
    }

    override suspend fun updateUser(user: User): Either<ErrorApp, Boolean> {
        return dao.getUserById(user.id)?.apply {
            dao.saveUser(user.toEntity())
        }?.let { true.right() } ?: ErrorApp.DataError.left()
    }

    override suspend fun getUsers(): Either<ErrorApp, List<User>> {
        dao.getAllUser().apply {
            return if (this.isEmpty()) {
                ErrorApp.DataError.left()
            } else {
                this.map {
                    it.toDomain()
                }.right()
            }
        }
    }

    override suspend fun getUser(userId: Int): Either<ErrorApp, User> {
        dao.getUserById(userId).apply {
            return if (this == null) {
                ErrorApp.DataError.left()
            } else {
                this.toDomain().right()
            }
        }
    }

    override suspend fun clear() {
        dao.deleteAllUser()
    }
}