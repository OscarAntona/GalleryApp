package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class DeleteServerUserUseCase @Inject constructor(private val serverUserRepository: ServerUserRepository) {
    suspend operator fun invoke(id: Int): Either<ErrorApp, Boolean> {
        return serverUserRepository.deleteServerUser(id)
    }
}