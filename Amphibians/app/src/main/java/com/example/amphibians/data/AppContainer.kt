package com.example.amphibians.data

import com.example.amphibians.network.FrogApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val frogPhotosRepository: FrogPhotosRepository
}

class DefaultAppContainer : AppContainer {
    // base url
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"


    // use the retrofit builder to build a retrofit object using a kotlinx serilization converter

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    // next retrofit service object for creating api calls
    private val retrofitService: FrogApiService by lazy {
        retrofit.create(FrogApiService::class.java)
    }

    // DI implemtation for mars photos repository
    override val frogPhotosRepository: FrogPhotosRepository by lazy {
        NetworkFrogsRepository(retrofitService)
    }
}