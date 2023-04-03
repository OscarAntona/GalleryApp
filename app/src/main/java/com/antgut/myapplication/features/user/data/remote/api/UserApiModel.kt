package com.antgut.myapplication.features.user.data.remote.api

import com.google.gson.annotations.SerializedName

data class UserApiModel(
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String
)