package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class UpdateServerUserUseCase @Inject constructor(
    private val serverUserRepository: ServerUserRepository
) {
    suspend operator fun invoke(user: User): Either<ErrorApp, Boolean> {
        return serverUserRepository.updateServerUser(user)
    }
}
