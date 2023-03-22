package com.antgut.myapplication.features.album.domain

import javax.inject.Inject

class SaveAlbumUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(album: Album) {
        return albumRepository.saveAlbum(album)
    }

}