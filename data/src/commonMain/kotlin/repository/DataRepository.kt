package repository

import Repository
import entity.Article
import entity.Source
import source.LocalSource
import source.NewsRemoteSource
import source.SampleSource

class DataRepository(
    private val sampleSource: SampleSource,
    private val localSource: LocalSource,
    private val newsRemoteSource: NewsRemoteSource
) : Repository {
    override fun getPlatform() = sampleSource.getPlatform()
    override suspend fun getArticles(): List<Article> {
        val localArticle = localSource.getArticles()
        if (localArticle.isEmpty()) {
            val articles = newsRemoteSource.fetchArticles()
            localSource.addArticles(articles)
            return articles
        }
        return localArticle
    }

    override suspend fun getSources(): List<Source> {
        val articleSource = localSource.getSources()
        if (articleSource.isEmpty()) {
            val articleSources = newsRemoteSource.fetchSources()
            localSource.addSources(articleSources)
            return articleSources
        }
        return articleSource
    }


}