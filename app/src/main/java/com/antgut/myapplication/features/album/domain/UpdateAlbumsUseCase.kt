package com.antgut.myapplication.features.album.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class UpdateAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(album: Album): Either<ErrorApp, Boolean> {
        return albumRepository.updateAlbum(album)
    }
}