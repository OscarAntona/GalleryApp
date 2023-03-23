package com.antgut.myapplication.features.photo.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class UpdatePhotoUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(photo: Photo): Either<ErrorApp, Boolean> {
        return photoRepository.updatePhoto(photo)
    }
}
