package source

import entity.Article
import entity.Source


interface NewsRemoteSource {

    suspend fun fetchSources(): List<Source>

    suspend fun fetchArticles(): List<Article>
}