package com.example.amphibians.ui.screens

import android.media.Image

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.model.FrogPhoto

@Composable
fun HomeScreen(
    frogUiState: FrogUiState,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
){
    when(frogUiState){
        is FrogUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is FrogUiState.Success -> PhotosGridScreen(frogUiState.photos,modifier = modifier, contentPadding = contentPadding)
        is FrogUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }

}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text(   modifier = modifier.size(200.dp),text="Loading")
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error occurred", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text="retry")
        }
    }
}
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}
@Composable
fun FrogPhotoCard(photo: FrogPhoto, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = "frogs",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)


            )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = photo.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = photo.type,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = photo.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun PhotosGridScreen(photos: List<FrogPhoto>, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)){

    LazyColumn(

        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding
    ) {
        items(count = photos.size, key = {index -> photos[index].name}) {index->
            val photo = photos[index]
            FrogPhotoCard(
                photo, modifier = modifier.padding(4.dp).fillMaxWidth()

            )
        }
    }

}