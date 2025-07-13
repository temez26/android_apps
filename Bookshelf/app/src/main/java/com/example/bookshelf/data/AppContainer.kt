package com.example.bookshelf.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

// here comes the DI container at the application level
interface AppContainer {
}


// here comes implementation for the dependency injection container at the application level
class DefaultAppContainer : AppContainer {
    // first baseurl
    private val baseUrl = "https://www.googleapis.com/books/v1/volumes?q="

    // use retrofit builder to build retrofit object using serialization converter
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

}