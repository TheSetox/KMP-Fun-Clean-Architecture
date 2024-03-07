import entity.Article
import entity.Source


data class Platform(
    val name: String
)

interface Repository {
    fun getPlatform(): Platform

    suspend fun getArticles(): List<Article>

    suspend fun getSources(): List<Source>
}