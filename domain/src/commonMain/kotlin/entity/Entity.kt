package entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String?,
    @SerialName("publishedAt") val date: String,
    @SerialName("urlToImage") val imageUrl: String?
)

@Serializable
data class Source(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val desc: String,
    @SerialName("language") val language: String,
    @SerialName("country") val country: String,
)