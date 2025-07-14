package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookshelf.ui.screens.shared.BooksScreen
import com.example.bookshelf.ui.screens.shared.ErrorScreen
import com.example.bookshelf.ui.screens.shared.LoadingScreen
import com.example.bookshelf.ui.screens.viewmodels.BookUiState


@Composable
fun HomeScreen(
 booksUiState: BookUiState,
 retryAction: () -> Unit,
 modifier: Modifier = Modifier,
 contentPadding: PaddingValues = PaddingValues(0.dp)
) {
 when (booksUiState) {
  is BookUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
  is BookUiState.Success -> BooksScreen(
   books = booksUiState.books,
   modifier = modifier.fillMaxSize(),
   contentPadding = contentPadding
  )
  is BookUiState.Error -> ErrorScreen(
   retryAction = retryAction,
   modifier = modifier.fillMaxSize()
  )
 }
}