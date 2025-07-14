package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class BookApiResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<BookVolume>
)

@Serializable
data class BookVolume(
    val kind: String,
    val id: String,
    val etag: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo? = null,
    val accessInfo: AccessInfo? = null,
    val searchInfo: SearchInfo? = null

)

@Serializable
data class VolumeInfo(
    val title: String? = null,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val industryIdentifiers: List<IndustryIdentifier>? = null,
    val readingModes: ReadingModes? = null,
    val pageCount: Int? = null,
    val printType: String? = null,
    val categories: List<String>? = null,
    val averageRating: Double? = null,
    val ratingsCount: Int? = null,
    val maturityRating: String? = null,
    val allowAnonLogging: Boolean? = null,
    val contentVersion: String? = null,
    val panelizationSummary: PanelizationSummary? = null,
    val imageLinks: ImageLinks? = null,
    val language: String? = null,
    val previewLink: String? = null,
    val infoLink: String? = null,
    val canonicalVolumeLink: String? = null
)

@Serializable
data class IndustryIdentifier(
    val type: String,
    val identifier: String
)

@Serializable
data class ReadingModes(
    val text: Boolean,
    val image: Boolean
)

@Serializable
data class PanelizationSummary(
    val containsEpubBubbles: Boolean,
    val containsImageBubbles: Boolean
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String? = null,
    val thumbnail: String? = null
)

@Serializable
data class SaleInfo(
    val country: String,
    val saleability: String,
    val isEbook: Boolean,
    val listPrice: Price? = null,
    val retailPrice: Price? = null,
    val buyLink: String? = null,
    val offers: List<Offer>? = null
)

@Serializable
data class Price(
    val amount: Double,
    val currencyCode: String
)

@Serializable
data class Offer(
    val finskyOfferType: Int,
    val listPrice: OfferPrice,
    val retailPrice: OfferPrice
)

@Serializable
data class OfferPrice(
    val amountInMicros: Long,
    val currencyCode: String
)

@Serializable
data class AccessInfo(
    val country: String,
    val viewability: String,
    val embeddable: Boolean,
    val publicDomain: Boolean,
    val textToSpeechPermission: String,
    val epub: EpubInfo,
    val pdf: PdfInfo,
    val webReaderLink: String,
    val accessViewStatus: String,
    val quoteSharingAllowed: Boolean
)

@Serializable
data class EpubInfo(
    val isAvailable: Boolean,
    val acsTokenLink: String? = null
)

@Serializable
data class PdfInfo(
    val isAvailable: Boolean,
    val acsTokenLink: String? = null
)

@Serializable
data class SearchInfo(
    val textSnippet: String
)