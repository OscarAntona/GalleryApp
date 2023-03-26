package com.antgut.myapplication.features.album.data.remote.api


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApiEndPoints {
    @GET("users/{userId}/albums")
    suspend fun getAlbums(@Path("userId")userId:Int): Response<List<AlbumApiModel>>
}