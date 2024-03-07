package data.source

import raw.data.Article
import raw.data.Source

interface LocalSource {

    fun getSources(): List<Source>

    fun clearSources()

    fun addSources(sources: List<Source>)

    fun getArticles(): List<Article>

    fun addArticles(articles: List<Article>)

    fun clearArticles()
}