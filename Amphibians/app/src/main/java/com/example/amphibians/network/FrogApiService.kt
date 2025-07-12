package com.example.amphibians.network

import com.example.amphibians.model.FrogPhoto
import retrofit2.http.GET

interface FrogApiService {

    @GET("amphibians")
    suspend fun getPhotos(): List<FrogPhoto>

}