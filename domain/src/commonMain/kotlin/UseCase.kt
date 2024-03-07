import entity.Article
import entity.Source


/**
 * Main executor for usecase.
 */
class MyUseCase(val repository: Repository)

suspend infix fun <T> MyUseCase.fetches(useCase: UseCase<T>): T {
    return repository.useCase()
}


val platformName: Repository.() -> String = {
    // Return
    getPlatform().name
}

val articles: ArticleUseCase = {
    val articles = getArticles()
    if (articles.isEmpty()) emptyResult// Return

    Result.success(articles) // Return
}

val articleSources: SourceUseCase = {
    val sources = getSources()
    if (sources.isEmpty()) emptyResult // Return

    Result.success(sources) // Return
}

private val emptyResult = Result.failure<Exception>(Exception("Empty result"))


typealias UseCase<ExpectedResult> = suspend Repository.() -> ExpectedResult

typealias ArticleUseCase = UseCase<ArticleResult>
typealias SourceUseCase = UseCase<SourceResult>

typealias SourceResult = Result<List<Source>>
typealias ArticleResult = Result<List<Article>>


