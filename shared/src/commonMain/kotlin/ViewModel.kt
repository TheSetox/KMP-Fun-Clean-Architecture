class ViewModel(private val mainScreen: MainScreen = MainScreen()) {

    fun getName(): MainState {
        val result = mainScreen fetches platformName
        val state = MainState()

        if (result.isEmpty()) state.error = "Error can't fetch data"

        if (result.isNotEmpty()) state.success = result

        return state
    }
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