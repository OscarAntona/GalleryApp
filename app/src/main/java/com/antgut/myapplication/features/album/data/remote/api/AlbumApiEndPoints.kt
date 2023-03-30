package com.antgut.myapplication.features.album.data.remote.api


import retrofit2.Response
import retrofit2.http.GET

interface AlbumApiEndPoints {
    @GET("albums")
    suspend fun getAlbums(): Response<List<AlbumApiModel>>
}