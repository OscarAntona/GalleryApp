package com.antgut.myapplication.features.user.data.remote.api

import com.antgut.myapplication.features.user.domain.User


fun UserApiModel.toDomain(): User {
    return User(
        this.id,
        this.name,
        this.username,
        this.email
    )
}