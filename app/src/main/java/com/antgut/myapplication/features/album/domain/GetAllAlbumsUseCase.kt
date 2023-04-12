package com.antgut.myapplication.features.album.domain

import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.funcional.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(): Either<ErrorApp, Flow<List<Album>>> {
        return albumRepository.getAllAlbums()
    }
}
