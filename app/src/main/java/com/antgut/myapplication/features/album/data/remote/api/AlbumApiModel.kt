package com.antgut.myapplication.features.album.data.remote.api

import com.google.gson.annotations.SerializedName

data class AlbumApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("title") val title: String,
)