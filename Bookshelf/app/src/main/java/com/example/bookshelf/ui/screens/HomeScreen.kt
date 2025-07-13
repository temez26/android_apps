package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.BookVolume
import com.example.bookshelf.ui.theme.BookshelfTheme

@Composable
fun HomeScreen(
 bookUiState: BookUiState,
 retryAction: () -> Unit,
 modifier: Modifier = Modifier
) {
 when (bookUiState) {
  is BookUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
  is BookUiState.Success -> BookPhotosGridScreen(
   books = bookUiState.books,
   modifier = modifier.fillMaxWidth()
  )
  is BookUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
 }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {

}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
 Column(
  modifier = modifier,
  verticalArrangement = Arrangement.Center,
  horizontalAlignment = Alignment.CenterHorizontally
 ) {

  Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
  Button(onClick = retryAction) {
   Text(stringResource(R.string.retry))
  }
 }
}

@Composable
fun BookPhotosGridScreen(
 books: List<BookVolume>,
 modifier: Modifier = Modifier
) {
 LazyVerticalGrid(
  columns = GridCells.Adaptive(150.dp),
  modifier = modifier.padding(horizontal = 4.dp),
  contentPadding = PaddingValues(4.dp)
 ) {
  items(items = books, key = { book -> book.id }) { book ->
   BookCard(
    book = book,
    modifier = Modifier
     .padding(4.dp)
     .fillMaxWidth()
     .aspectRatio(1.5f)
   )
  }
 }
}

@Composable
fun BookCard(book: BookVolume, modifier: Modifier = Modifier) {
 Card(
  modifier = modifier
 ) {
  Column {
   AsyncImage(
    model = ImageRequest.Builder(context = LocalContext.current)
     .data(book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"))
     .crossfade(true)
     .build(),
    error = painterResource(R.drawable.ic_broken_image),
    placeholder = painterResource(R.drawable.loading_img),
    contentDescription = book.volumeInfo.title,
    contentScale = ContentScale.Crop,
    modifier = Modifier
     .fillMaxWidth()
     .height(194.dp)
   )

   Text(
    text = book.volumeInfo.title,
    style = MaterialTheme.typography.titleMedium,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    modifier = Modifier
     .padding(8.dp)
     .fillMaxWidth()
   )
  }
 }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
 BookshelfTheme {
  LoadingScreen()
 }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
 BookshelfTheme {
  ErrorScreen({})
 }
}