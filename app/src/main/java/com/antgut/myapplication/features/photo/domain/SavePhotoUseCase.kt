package com.antgut.myapplication.features.photo.domain

import javax.inject.Inject

class SavePhotoUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(photo: Photo) {
        return photoRepository.savePhoto(photo)
    }
}