package di

import MyUseCase
import org.koin.dsl.module

val domainModule = module {
    single<MyUseCase> { MyUseCase(get()) }
}