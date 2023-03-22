package com.antgut.myapplication.features.user.domain

import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        return userRepository.saveUser(user)
    }

}