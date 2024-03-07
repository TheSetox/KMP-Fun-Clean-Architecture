package di

import DataRepository
import Repository
import data.source.LocalSource
import data.source.SampleSource
import data.source.impl.LocalDataSource
import data.source.impl.SampleDataSource
import org.example.project.db.SampleDataBase
import org.koin.dsl.module

val localModule = module {
    single<LocalSource> { LocalDataSource(get()) }
    single<SampleDataBase> { SampleDataBase(get()) }
    single<Repository> { DataRepository(get()) }
}

val sampleModule = module {
    single<SampleSource> { SampleDataSource() }
}

val dataModule = listOf(
    localModule,
    sampleModule
)