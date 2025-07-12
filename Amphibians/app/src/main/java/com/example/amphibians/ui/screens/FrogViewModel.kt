package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.amphibians.FrogPhotosApplication
import com.example.amphibians.data.FrogPhotosRepository
import com.example.amphibians.model.FrogPhoto
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface FrogUiState{
    data class Success(val photos: List<FrogPhoto>) : FrogUiState
    object Error : FrogUiState
    object Loading : FrogUiState
}

class FrogViewModel(private val frogPhotosRepository: FrogPhotosRepository) : ViewModel() {
    var frogUiState: FrogUiState by mutableStateOf(FrogUiState.Loading)
        private set

    // call the getFrogPhotos so we can display the status immediately.

    init {
        getFrogPhotos()
    }


    // Gets the Frog pohotos info from the frog api and then updates the marsphoto list with the data form api

    fun getFrogPhotos(){
        viewModelScope.launch{
            frogUiState = FrogUiState.Loading
            frogUiState = try {
                val result = frogPhotosRepository.getFrogPhotos()[0]
                FrogUiState.Success(
                    frogPhotosRepository.getFrogPhotos()
                )
            } catch (e: IOException){
                FrogUiState.Error
            } catch (e: HttpException){
                FrogUiState.Error
            }
        }
    }

    // factory for frogviewmodel that takes frogphotosrepository as dependency
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FrogPhotosApplication)
                val frogPhotosRepository = application.container.frogPhotosRepository
                FrogViewModel(frogPhotosRepository = frogPhotosRepository)

            }
        }
    }
}