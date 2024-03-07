package data.source.impl

import data.source.LocalSource
import org.example.project.db.SampleDataBase
import raw.data.Article
import raw.data.Source

class LocalDataSource(private val db: SampleDataBase) : LocalSource {
    override fun getSources(): List<Source> {
        return db.sourcesQueries.selectSources { id, name, description, language, country ->
            Source(id, name, description, language, country)
        }.executeAsList()
    }

    override fun clearSources() = db.sourcesQueries.removeAllSources()

    override fun addSources(sources: List<Source>) {
        db.sourcesQueries.transaction {
            sources.forEach {
                db.sourcesQueries.insertSource(
                    it.id,
                    it.name,
                    it.desc,
                    it.language,
                    it.country
                )
            }
        }
    }

    override fun getArticles(): List<Article> {
        return db.articlesQueries.selectArticles { title, description, date, imageUrl ->
            Article(title, description, date, imageUrl)
        }.executeAsList()
    }

    override fun addArticles(articles: List<Article>) {
        db.articlesQueries.transaction {
            articles.forEach {
                db.articlesQueries.insertArticle(
                    it.title,
                    it.description,
                    it.date,
                    it.imageUrl
                )
            }
        }
    }

    override fun clearArticles() = db.articlesQueries.removeAllArticles()
}