package source

import entity.Article
import entity.Source

interface LocalSource {

    fun getSources(): List<Source>

    fun clearSources()

    fun addSources(sources: List<Source>)

    fun getArticles(): List<Article>

    fun addArticles(articles: List<Article>)

    fun clearArticles()
}