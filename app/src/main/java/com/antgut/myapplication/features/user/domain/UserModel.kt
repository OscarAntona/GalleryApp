package com.antgut.myapplication.features.user.domain

data class User(
    val id: Int?=null,
    val name: String,
    val username: String,
    val email: String,
)