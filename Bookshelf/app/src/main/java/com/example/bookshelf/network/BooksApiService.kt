package com.example.bookshelf.network

import com.example.bookshelf.model.BookApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    // now get list of books from the API

    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String = "history+jazz"): BookApiResponse
}