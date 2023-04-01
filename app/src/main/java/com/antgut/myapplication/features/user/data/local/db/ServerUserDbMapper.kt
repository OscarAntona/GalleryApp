package com.antgut.myapplication.features.user.data.local.db

import com.antgut.myapplication.features.user.domain.User

fun ServerUserEntity.toDomain(): User {
    return User(
        this.id,
        this.serverId,
        this.name,
        this.username,
        this.email,
    )
}

fun User.toEntity(): ServerUserEntity {
    return ServerUserEntity(
        this.id,
        this.serverId,
        this.name,
        this.username,
        this.email
    )
}