package com.antgut.myapplication.features.photo.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface PhotoApiEndPoints {
    @GET("photos")
    suspend fun getPhotos(): Response<List<PhotoApiModel>>
}