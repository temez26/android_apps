package com.example.amphibians.data

import com.example.amphibians.model.FrogPhoto
import com.example.amphibians.network.FrogApiService

interface FrogPhotosRepository {


    suspend fun getFrogPhotos(): List<FrogPhoto>

}

class NetworkFrogsRepository(
    private val frogApiService: FrogApiService
) : FrogPhotosRepository {
    override suspend fun getFrogPhotos(): List<FrogPhoto> = frogApiService.getPhotos()
}