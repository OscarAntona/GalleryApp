package com.antgut.myapplication.features.photo.data.local.db

import com.antgut.myapplication.features.photo.domain.Photo

fun PhotoEntity.toDomain(): Photo {
    return Photo(
        this.id,
        this.serverId,
        this.albumId,
        this.title,
        this.url,
        this.thumbnailUrl
    )
}

fun Photo.toEntity(): PhotoEntity {
    return PhotoEntity(
        this.id,
        this.serverId,
        this.albumId,
        this.title,
        this.url,
        this.thumbnailUrl,
    )
}