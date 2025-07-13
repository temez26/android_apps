package com.example.bookshelf.network

import com.example.bookshelf.model.BookApiResponse
import com.example.bookshelf.model.Book
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    // Get list of books from the API
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String = "jazz+history"): BookApiResponse

}