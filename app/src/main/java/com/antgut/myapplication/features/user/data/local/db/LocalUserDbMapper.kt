package com.antgut.myapplication.features.user.data.local.db

import com.antgut.myapplication.features.user.domain.User

fun LocalUserEntity.toDomain(): User {
    return User(
        this.id,
        this.name,
        this.username,
        this.email,
    )
}

fun User.toLocalEntity(): LocalUserEntity {
    return LocalUserEntity(
        this.id,
        this.name,
        this.username,
        this.email
    )
}