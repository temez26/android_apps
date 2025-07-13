package com.example.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.screens.BookViewModel
import com.example.bookshelf.ui.screens.BookshelfTopAppBar
import com.example.bookshelf.ui.screens.HomeScreen

@Composable
fun BookPhotosApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val bookViewModel: BookViewModel = viewModel(factory = BookViewModel.Factory)

        Scaffold(
            topBar = {
                BookshelfTopAppBar()
            }
        ) { innerPadding ->
            HomeScreen(
                bookUiState = bookViewModel.bookUiState,
                retryAction = bookViewModel::getBooks,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}