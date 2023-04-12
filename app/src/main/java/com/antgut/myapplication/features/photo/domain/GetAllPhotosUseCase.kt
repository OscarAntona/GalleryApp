package com.antgut.myapplication.features.photo.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPhotosUseCase @Inject constructor(private val repository: PhotoRepository) {
    suspend operator fun invoke(): Either<ErrorApp, Flow<List<Photo>>> {
        return repository.getAllPhotos()
    }
}


