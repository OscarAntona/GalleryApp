package com.antgut.myapplication.features.user.domain

import javax.inject.Inject

class SaveServerUserUseCase @Inject constructor(
    private val serverUserRepository: ServerUserRepository
) {
    suspend operator fun invoke(user: User) {
        return serverUserRepository.saveServerUser(user)
    }

}