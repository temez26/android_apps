package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
 Column(
  modifier = modifier,
  verticalArrangement = Arrangement.Center,
  horizontalAlignment = Alignment.CenterHorizontally
 ) {
  CircularProgressIndicator()
  Text(
   text = "Loading...",
   modifier = Modifier.padding(16.dp),
   style = MaterialTheme.typography.titleMedium
  )
 }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
 Column(
  modifier = modifier,
  verticalArrangement = Arrangement.Center,
  horizontalAlignment = Alignment.CenterHorizontally
 ) {
  Text(
   text = "⚠️",
   style = MaterialTheme.typography.displayLarge,
   modifier = Modifier.padding(16.dp)
  )
  Text(
   text = "Loading failed",
   modifier = Modifier.padding(16.dp),
   style = MaterialTheme.typography.titleMedium
  )
  Button(onClick = retryAction) {
   Text("Retry")
  }
 }
}

@Composable
fun BookPhotosGridScreen(
 books: List<BookVolume>,
 modifier: Modifier = Modifier
) {
 LazyVerticalGrid(
  columns = GridCells.Adaptive(minSize = 180.dp), // Optimized for better book display
  modifier = modifier.padding(horizontal = 8.dp),
  contentPadding = PaddingValues(8.dp),
  horizontalArrangement = Arrangement.spacedBy(8.dp),
  verticalArrangement = Arrangement.spacedBy(8.dp)
 ) {
  items(items = books, key = { book -> book.id }) { book ->
   BookCard(
    book = book,
    modifier = Modifier.fillMaxWidth()
   )
  }
 }
}

@Composable
fun BookCard(book: BookVolume, modifier: Modifier = Modifier) {
 Card(
  modifier = modifier
   .height(300.dp) // Increased height for better image display
 ) {
  Column(
   modifier = Modifier
    .padding(8.dp)
    .fillMaxHeight()
  ) {
   // Book cover image with better sizing
   Card(
    modifier = Modifier
     .fillMaxWidth()
     .height(180.dp) // Increased height for better image display
   ) {
    AsyncImage(
     model = ImageRequest.Builder(context = LocalContext.current)
      .data(book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://"))
      .crossfade(true)
      .build(),
     contentDescription = book.volumeInfo.title,
     contentScale = ContentScale.Fit, // Changed to Fit to show full image
     modifier = Modifier.fillMaxSize()
    )
   }

   Spacer(modifier = Modifier.height(8.dp))

   // Title - with weight to fill remaining space
   Text(
    text = book.volumeInfo.title,
    style = MaterialTheme.typography.titleSmall,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Start,
    maxLines = 2,
    overflow = TextOverflow.Ellipsis,
    modifier = Modifier
     .fillMaxWidth()
     .weight(1f) // This takes up remaining space
   )

   Spacer(modifier = Modifier.height(4.dp))

   // Authors - fixed at bottom
   book.volumeInfo.authors?.let { authors ->
    Text(
     text = "by ${authors.joinToString(", ")}",
     style = MaterialTheme.typography.bodySmall,
     color = MaterialTheme.colorScheme.onSurfaceVariant,
     maxLines = 1,
     overflow = TextOverflow.Ellipsis,
     modifier = Modifier.fillMaxWidth()
    )
   }

   Spacer(modifier = Modifier.height(4.dp))

   // Published date - fixed at bottom
   book.volumeInfo.publishedDate?.let { date ->
    Text(
     text = date,
     style = MaterialTheme.typography.bodySmall,
     color = MaterialTheme.colorScheme.onSurfaceVariant
    )
   }
  }
 }
}