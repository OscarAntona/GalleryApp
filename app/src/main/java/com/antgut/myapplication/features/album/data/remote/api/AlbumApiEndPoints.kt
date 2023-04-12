package com.antgut.myapplication.features.album.data.remote.api


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApiEndPoints {
    @GET("albums")
    suspend fun getAlbums(): Response<List<AlbumApiModel>>

    @GET("users/{user_id}/albums")
    suspend fun getAlbumsByUser(@Path("user_id") userId: Int): Response<List<AlbumApiModel>>
}