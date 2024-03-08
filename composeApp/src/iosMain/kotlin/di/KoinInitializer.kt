package di

import ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val modules = sharedModule

    startKoin {
        modules(modules)
    }
}

class ViewModelInjector : KoinComponent {
    val viewModel: ViewModel by inject()
}