package com.antgut.myapplication.features.album.data.remote.api

import com.antgut.myapplication.features.album.domain.Album

fun AlbumApiModel.toDomain(): Album {
    return Album(
        serverId = this.id,
        userId = this.userId,
        title = this.title
    )
}