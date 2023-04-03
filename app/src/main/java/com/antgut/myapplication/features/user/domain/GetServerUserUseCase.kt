package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class GetServerUserUseCase @Inject constructor(
    private val serverUserRepository: ServerUserRepository
) {
    suspend operator fun invoke(id: Int): Either<ErrorApp, User> {
        return serverUserRepository.getServerUser(id)
    }
}