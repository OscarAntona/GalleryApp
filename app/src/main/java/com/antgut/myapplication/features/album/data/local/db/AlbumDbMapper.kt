package com.antgut.myapplication.features.album.data.local.db

import com.antgut.myapplication.features.album.domain.Album

fun AlbumEntity.toDomain(): Album {
    return Album(
        this.id,
        this.userId,
        this.title,
        this.serverId
    )
}

fun Album.toEntity(): AlbumEntity {
    return AlbumEntity(
        this.id,
        this.userId,
        this.title,
        this.serverId
    )
}