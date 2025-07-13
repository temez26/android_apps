package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class BookApiResponse(
    val items: List<BookVolume>
)

@Serializable
data class BookVolume(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val authors: List<String>? = null,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    val thumbnail: String? = null
)