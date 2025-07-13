package com.example.bookshelf.network

import com.example.bookshelf.model.BookApiResponse
import retrofit2.http.GET

interface BooksApiService {
    // now get list of books from the API

    @GET("jazz+history")
    suspend fun getBooks(): List<BookApiResponse>
}