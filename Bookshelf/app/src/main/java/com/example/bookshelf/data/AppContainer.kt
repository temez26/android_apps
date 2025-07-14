package com.example.bookshelf.data

import com.example.bookshelf.network.BooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

// here comes the DI container at the application level
interface AppContainer {
    val booksPhotosRepository: BooksPhotosRepository
}


// here comes implementation for the dependency injection container at the application level
class DefaultAppContainer : AppContainer {
    // first baseurl
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    // use retrofit builder to build retrofit object using serialization converter
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    // retrofit service object for creating api calls
    private val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }

    // DI implementation for Books photos repository
    override val booksPhotosRepository: BooksPhotosRepository by lazy {
        NetworkBooksPhotosRepository(retrofitService)
    }

}