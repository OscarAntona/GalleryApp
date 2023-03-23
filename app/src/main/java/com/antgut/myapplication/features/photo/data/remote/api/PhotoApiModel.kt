package com.antgut.myapplication.features.photo.data.remote.api

import com.google.gson.annotations.SerializedName

data class PhotoApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("albumId") val albumId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
)