package com.antgut.myapplication.features.user.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Either<ErrorApp, Flow<List<User>>> {
        return userRepository.getUsers()
    }

}
