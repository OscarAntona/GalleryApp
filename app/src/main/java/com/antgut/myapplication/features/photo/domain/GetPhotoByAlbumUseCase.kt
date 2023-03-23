package com.antgut.myapplication.features.photo.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class GetPhotoByAlbumUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(albumId: Int): Either<ErrorApp, Photo> {
        return photoRepository.getPhotoByAlbum(albumId)
    }
}
