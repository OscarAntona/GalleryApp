package com.antgut.myapplication.features.user.domain

import javax.inject.Inject

class SaveLocalUserUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) {
    suspend operator fun invoke(user: User) {
        return localUserRepository.saveLocalUser(user)
    }

}