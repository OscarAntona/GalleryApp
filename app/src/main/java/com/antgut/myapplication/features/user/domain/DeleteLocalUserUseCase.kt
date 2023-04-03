package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class DeleteLocalUserUseCase @Inject constructor(private val localUserRepository: LocalUserRepository) {
    suspend operator fun invoke(id: Int): Either<ErrorApp, Boolean> {
        return localUserRepository.deleteLocalUser(id)
    }
}