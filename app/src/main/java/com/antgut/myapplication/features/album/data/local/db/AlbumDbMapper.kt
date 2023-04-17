package com.antgut.myapplication.features.album.data.local.db

import com.antgut.myapplication.features.album.domain.Album

fun AlbumEntity.toDomain(): Album {
    return Album(
        this.id,
        this.serverId,
        this.userId,
        this.title,
    )
}

fun Album.toEntity(): AlbumEntity {
    return AlbumEntity(
        this.id,
        this.serverId,
        this.userId,
        this.title
    )
}