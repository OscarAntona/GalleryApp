package com.antgut.myapplication.features.user.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface UserApiEndPoints {
    @GET("users.json")
    suspend fun getUsers(): Response<List<UserApiModel>>
}