package com.antgut.myapplication.features.album.domain

data class Album(
    var id: Int? = null,
    val serverId: Int?=null,
    val userId: Int,
    val title: String,
)
