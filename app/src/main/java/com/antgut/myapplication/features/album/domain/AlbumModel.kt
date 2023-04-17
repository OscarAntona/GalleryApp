package com.antgut.myapplication.features.album.domain

data class Album(
    var id: Int? = null,
    val userId: Int,
    val title: String,
    val serverId: Boolean?=true,
)
