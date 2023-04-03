package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class GetServerUsersUseCase @Inject constructor(
    private val serverUserRepository: ServerUserRepository
) {
    suspend operator fun invoke(): Either<ErrorApp, List<User>> {
        return serverUserRepository.getServerUsers()
    }
}
