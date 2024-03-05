class ViewModel(private val mainScreen: MainScreen = MainScreen()) {

    fun getState(): MainState {
        val result = mainScreen fetches platformName
        return result.reduceToState()
    }
}

fun String.reduceToState(): MainState {
    val state = MainState()
    if (isEmpty()) state.error = "Error can't fetch data"
    if (isNotEmpty()) state.success = this
    return state
}

class MainScreen(private val repository: Repository = DataRepository()) {
    infix fun fetches(useCase: Repository.() -> String): String {
        return repository.useCase()
    }
}

data class MainState(
    var success: String = "",
    var error: String = ""
)