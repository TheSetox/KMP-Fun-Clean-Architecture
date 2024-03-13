import entity.Article
import entity.Source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModel(private val myUseCase: MyUseCase) : BaseViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    val state: StateFlow<State> get() = _state

    infix fun calls(intent: Intent) {
        when (intent) {
            LoadArticleSources -> loadArticleSources()
            LoadArticles -> loadArticles()
            Loading -> loading()
        }
    }

    private fun loading() = scope.launch {
        _state.emit(State.Loading)
    }

    private fun loadArticleSources() = scope.launch {
        val result = myUseCase fetches articleSources
        result.apply {
            onFailure { _state.emit(State.Error) }
            onSuccess { _state.emit(State.ArticleSourceState(it)) }
        }
    }

    private fun loadArticles() = scope.launch {
        val result = myUseCase fetches articles
        result.apply {
            onFailure { _state.emit(State.Error) }
            onSuccess { _state.emit(State.ArticleState(it)) }
        }
    }

}


sealed class State {
    data object Loading : State()
    data object Error : State()
    class ArticleSourceState(val sources: List<Source>) : State()
    class ArticleState(val articles: List<Article>) : State()
}

sealed class Intent
data object LoadArticles : Intent()
data object LoadArticleSources : Intent()

data object Loading : Intent()