package com.antgut.myapplication.features.album.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumsByUserUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(userId: Int): Either<ErrorApp, Flow<List<Album>>> {
        return albumRepository.getAlbumsByUser(userId)
    }
}