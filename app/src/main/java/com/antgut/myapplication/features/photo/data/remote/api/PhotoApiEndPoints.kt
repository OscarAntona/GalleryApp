package com.antgut.myapplication.features.photo.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoApiEndPoints {
    @GET("photos")
    suspend fun getPhotos(): Response<List<PhotoApiModel>>

    @GET("albums/{album_id}/photos")
    suspend fun getPhotosByAlbum(@Path("album_id") albumId: Int): Response<List<PhotoApiModel>>
}