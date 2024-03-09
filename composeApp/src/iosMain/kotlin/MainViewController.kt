import androidx.compose.ui.window.ComposeUIViewController
import di.ViewModelInjector

fun MainViewController() = ComposeUIViewController {
    val viewModel = ViewModelInjector().getViewModel()
    App(viewModel)
}