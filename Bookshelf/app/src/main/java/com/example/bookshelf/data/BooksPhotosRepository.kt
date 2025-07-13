package com.example.bookshelf.data

import com.example.bookshelf.model.BookApiResponse
import com.example.bookshelf.network.BooksApiService


interface BooksPhotosRepository {
    // fetches list of books from booksapiservice
    suspend fun getBooksPhotos(): List<BookApiResponse>
}

class NetworkBooksPhotosRepository(private val booksApiService: BooksApiService): BooksPhotosRepository {
    // fetches list of books from booksapiservice
    override suspend fun getBooksPhotos(): List<BookApiResponse> = booksApiService.getBooks()
}