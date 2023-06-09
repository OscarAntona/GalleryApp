package com.antgut.myapplication.features.album.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(albumId: Int): Either<ErrorApp, Album> {
        return albumRepository.getAlbum(albumId)
    }
}