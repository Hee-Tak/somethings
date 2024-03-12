package com.tak.something.data

import retrofit2.http.GET

interface ApiService {

    @GET("items")
    suspend fun getItems(): List<Item>
}