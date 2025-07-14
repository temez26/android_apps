package com.example.bookshelf.ui.screens.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.model.BookVolume

@Composable
fun BookCard(
    book: BookVolume,
    modifier: Modifier = Modifier,
    imageWeight: Float = 0.7f,
    infoWeight: Float = 0.3f,
    cornerRadius: Int = 12,
    cardElevation: Int = 4,
    contentPadding: Int = 8,
    titleMaxLines: Int = 3,
    authorsMaxLines: Int = 2
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Book Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(imageWeight)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(book.volumeInfo.imageLinks?.thumbnail?.replace("http:", "https:"))
                        .crossfade(true)
                        .build(),
                    contentDescription = book.volumeInfo.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Book Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(infoWeight)
                    .padding(contentPadding.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Title
                Text(
                    text = book.volumeInfo.title ?: "Unknown Title",
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = titleMaxLines,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = MaterialTheme.typography.titleSmall.lineHeight,
                    modifier = Modifier.weight(1f, fill = false)
                )

                // Authors
                book.volumeInfo.authors?.let { authors ->
                    Text(
                        text = authors.joinToString(", "),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = authorsMaxLines,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}