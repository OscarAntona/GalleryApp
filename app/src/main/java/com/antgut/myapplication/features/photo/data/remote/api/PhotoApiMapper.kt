package com.antgut.myapplication.features.photo.data.remote.api

import com.antgut.myapplication.features.photo.domain.Photo

fun PhotoApiModel.toDomain(): Photo {
    return Photo(
        serverId = this.id,
        albumId = this.albumId,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl
    )
}