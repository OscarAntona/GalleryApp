package com.antgut.myapplication.features.photo.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class DeletePhotoUseCase @Inject constructor(private val repository: PhotoRepository) {
    suspend operator fun invoke(photoId: Int): Either<ErrorApp, Boolean> {
        return repository.deletePhoto(photoId)
    }
}