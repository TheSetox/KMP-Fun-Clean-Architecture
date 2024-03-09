package di

import ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(iOsSharedModule)
    }
}

class ViewModelInjector : KoinComponent {
    private val viewModel: ViewModel by inject()
    fun getViewModel() = viewModel
}