package com.example.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.screens.FrogViewModel
import com.example.amphibians.ui.screens.HomeScreen




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrogPhotosApp(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {FrogTopBar(scrollBehavior = scrollBehavior)}
    ){
        Surface(modifier = Modifier.fillMaxSize()){
            val frogViewModel: FrogViewModel = viewModel(factory=FrogViewModel.Factory)
            HomeScreen(
                frogUiState = frogViewModel.frogUiState,
                retryAction = frogViewModel::getFrogPhotos,
                contentPadding = it
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrogTopBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Frogs",
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )

}