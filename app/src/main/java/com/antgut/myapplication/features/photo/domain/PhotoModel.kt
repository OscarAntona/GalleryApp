package com.antgut.myapplication.features.photo.domain

data class Photo(
    val id: Int? = null,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)




