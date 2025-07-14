package com.example.bookshelf.data

import com.example.bookshelf.model.BookApiResponse
import com.example.bookshelf.model.BookVolume
import com.example.bookshelf.network.BooksApiService


interface BooksPhotosRepository {
    // fetches list of books from booksapiservice
    suspend fun getBooksPhotos(): BookApiResponse
}

class NetworkBooksPhotosRepository(private val booksApiService: BooksApiService): BooksPhotosRepository {
    // fetches list of books from booksapiservice
    override suspend fun getBooksPhotos(): BookApiResponse = booksApiService.getBooks()
}