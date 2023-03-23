package com.antgut.myapplication.features.photo.data.remote.api

import com.antgut.myapplication.features.photo.domain.Photo

fun PhotoApiModel.toDomain(): Photo {
    return Photo(
        this.id,
        this.albumId,
        this.title,
        this.url,
        this.thumbnailUrl
    )
}