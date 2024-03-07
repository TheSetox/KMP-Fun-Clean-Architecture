import entity.Article
import entity.Source
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: String,
    @SerialName("articles") val articles: List<Article>
)

@Serializable
data class SourcesResponse(
    @SerialName("status") val status: String,
    @SerialName("sources") val sources: List<Source>,
)