package di

import ViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<ViewModel> { ViewModel(get()) }
}

val sharedModule = viewModelModule + domainModule + dataModule