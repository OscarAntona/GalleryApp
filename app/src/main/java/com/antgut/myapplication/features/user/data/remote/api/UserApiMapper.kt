package com.antgut.myapplication.features.user.data.remote.api

import com.antgut.myapplication.features.user.domain.User


fun UserApiModel.toDomain(): User {
    return User(
        name=this.name,
        username = this.username,
        email = this.email
    )
}