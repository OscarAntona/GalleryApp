package com.antgut.myapplication.features.photo.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) {
    suspend operator fun invoke(albumId: Int): Either<ErrorApp, List<Photo>> {
        return repository.getPhotos(albumId)
    }
}
