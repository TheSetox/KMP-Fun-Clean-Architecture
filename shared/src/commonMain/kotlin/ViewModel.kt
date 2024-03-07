import entity.Source

class ViewModel(private val myUseCase: MyUseCase) {

    suspend fun getState(): MainState {
        val result = myUseCase fetches articleSources
        return result.reduceToState()
    }
}

fun SourceResult.reduceToState(): MainState {
    val state = MainState()
    onFailure { state.error = it.message.toString() }
    onSuccess { state.success = it }
    return state
}


data class MainState(
    var success: List<Source> = emptyList(),
    var error: String = ""
)