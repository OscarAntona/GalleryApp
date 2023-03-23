package com.antgut.myapplication.features.album.data.remote.api

import com.antgut.myapplication.features.album.domain.Album

fun AlbumApiModel.toDomain(): Album {
    return Album(
        this.id,
        this.userId,
        this.title
    )
}