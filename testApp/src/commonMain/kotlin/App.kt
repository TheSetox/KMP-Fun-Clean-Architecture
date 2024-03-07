import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import entity.Article
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
    myUseCase: MyUseCase
) {
    val remoteSources: MutableState<List<Article>> = remember {
        mutableStateOf(emptyList())
    }
    LaunchedEffect(Unit) {
        val sources = myUseCase fetches articles

        sources.onFailure {
            // TODO Add failure
        }

        sources.onSuccess {
            remoteSources.value = it
        }
    }
    MaterialTheme {
        Column {
            LazyColumn {
                items(remoteSources.value) { sources ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Source Platform: ${sampleSource.getPlatform()}")
                        Text("Repository Platform: ${repository.getPlatform()}")
                        Text("Title: ${sources.title}")
                        Text("Description: ${sources.description}")
                        Text("Publish At: ${sources.date}")
                        Text("Image url: ${sources.imageUrl}")
                    }
                }
            }
        }
    }
}