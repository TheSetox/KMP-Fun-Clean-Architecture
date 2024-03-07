package source.impl

import ArticlesResponse
import SourcesResponse
import entity.Article
import entity.Source
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import source.NewsRemoteSource

class NewsRemoteDataSource(private val httpClient: HttpClient) : NewsRemoteSource {
    override suspend fun fetchSources(): List<Source> {
        val response: SourcesResponse = httpClient.get(sourcesApi).body()
        return response.sources
    }

    override suspend fun fetchArticles(): List<Article> {
        val response: ArticlesResponse = httpClient.get(articleApi).body()
        return response.articles
    }

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2"

        private const val TOP_HEADLINES = "$BASE_URL/top-headlines"

        private const val COUNTRY = "country=ph"
        private const val CATEGORY = "category=technology"
        private val apiKey = "apiKey=${getApiKey()}"

        private val sourcesApi = "$TOP_HEADLINES/sources?$apiKey"
        private val articleApi = "$TOP_HEADLINES?$COUNTRY&$CATEGORY&$apiKey"

    }
}

expect fun getApiKey(): String