package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: Int): Either<ErrorApp, Boolean> {
        return userRepository.deleteUser(userId)
    }
}