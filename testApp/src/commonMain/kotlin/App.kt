import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import source.LocalSource
import source.NewsRemoteSource
import source.SampleSource

@Composable
@Preview
fun App(
    sampleSource: SampleSource,
    repository: Repository,
    localSource: LocalSource,
    newsRemoteSource: NewsRemoteSource,
    myUseCase: MyUseCase,
    viewModel: ViewModel
) {
    val state = viewModel.state.collectAsState()
    viewModel calls LoadArticles
    MaterialTheme {
        Column {
            if (state.value is State.ArticleState) {
                LazyColumn {
                    items((state.value as State.ArticleState).articles) { source ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text("Source Platform: ${sampleSource.getPlatform()}")
                            Text("Repository Platform: ${repository.getPlatform()}")
                            Text("Title: ${source.title}")
                            Text("Description: ${source.description}")
                            Text("Publish At: ${source.date}")
                            Text("Image url: ${source.imageUrl}")
                        }
                    }
                }
            } else {
                Text("Not suported")
            }
        }
    }
}