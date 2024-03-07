package di

import Repository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.db.SampleDataBase
import org.koin.dsl.module
import repository.DataRepository
import source.LocalSource
import source.NewsRemoteSource
import source.SampleSource
import source.impl.LocalDataSource
import source.impl.NewsRemoteDataSource
import source.impl.SampleDataSource

val localModule = module {
    single<LocalSource> { LocalDataSource(get()) }
    single<SampleDataBase> { SampleDataBase(get()) }
    single<Repository> { DataRepository(get(), get(), get()) }
}

val sampleModule = module {
    single<SampleSource> { SampleDataSource() }
}

val remoteModule = module {
    single<NewsRemoteSource> { NewsRemoteDataSource(get()) }
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}

val dataModule = listOf(
    localModule,
    sampleModule,
    remoteModule
)