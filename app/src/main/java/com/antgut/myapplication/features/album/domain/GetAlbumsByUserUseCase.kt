package com.antgut.myapplication.features.album.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class GetAlbumsByUserUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(userId: Int): Either<ErrorApp, List<Album>> {
        return albumRepository.getAlbumsByUser(userId)
    }
}