package com.antgut.myapplication.features.photo.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoApiEndPoints {
    @GET("album/{albumId}/photos")
    suspend fun getPhotos(@Path("albumId") albumId: Int): Response<List<PhotoApiModel>>
}