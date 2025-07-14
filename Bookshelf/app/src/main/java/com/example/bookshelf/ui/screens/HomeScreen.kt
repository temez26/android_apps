package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.shared.BooksScreen
import com.example.bookshelf.ui.screens.shared.StateScreen
import com.example.bookshelf.ui.screens.viewmodels.BookUiState

@Composable
fun HomeScreen(
    booksUiState: BookUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (booksUiState) {
        is BookUiState.Loading -> StateScreen(
            showLoading = true,
            modifier = modifier.fillMaxSize()
        )
        is BookUiState.Success -> BooksScreen(
            books = booksUiState.books,
            modifier = modifier.fillMaxSize(),
            contentPadding = contentPadding
        )
        is BookUiState.Error -> StateScreen(
            message = stringResource(R.string.error_message),
            buttonText = stringResource(R.string.retry),
            onButtonClick = retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}