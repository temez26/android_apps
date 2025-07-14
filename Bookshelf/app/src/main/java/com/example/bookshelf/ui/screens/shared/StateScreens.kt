package com.example.bookshelf.ui.screens.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bookshelf.R

@Composable
fun StateScreen(
    modifier: Modifier = Modifier,
    showLoading: Boolean = false,
    message: String? = null,
    buttonText: String? = null,
    onButtonClick: (() -> Unit)? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        if (showLoading) {
            CircularProgressIndicator()
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                message?.let { msg ->
                    Text(
                        text = msg,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                
                if (buttonText != null && onButtonClick != null) {
                    Button(
                        onClick = onButtonClick,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = buttonText)
                    }
                }
            }
        }
    }
}