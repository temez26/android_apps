package com.example.bookshelf.ui.screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BooksPhotosApplication
import com.example.bookshelf.data.BooksPhotosRepository
import com.example.bookshelf.model.BookVolume
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// UI state for the home screen
sealed interface BookUiState {
    data class Success(val books: List<BookVolume>) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}

// ViewModel containing the app data and method to get the data
class BookViewModel(private val booksPhotosRepository: BooksPhotosRepository) : ViewModel() {
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            bookUiState = BookUiState.Loading
            bookUiState = try {
                val response = booksPhotosRepository.getBooksPhotos()
                BookUiState.Success(response.items)
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BooksPhotosApplication)
                val booksPhotosRepository = application.container.booksPhotosRepository
                BookViewModel(booksPhotosRepository = booksPhotosRepository)
            }
        }
    }
}