import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import entity.Article
import entity.Source
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: ViewModel) {
    val state = viewModel.state.collectAsState()
    viewModel calls LoadArticles
    MaterialTheme {
        Column {
            when (val stateValue = state.value) {
                is State.ArticleSourceState -> ArticleSourcesContent(stateValue.sources)
                is State.ArticleState -> ArticleContent(stateValue.articles)
                State.Error -> Error()
                State.Loading -> Loading()
            }
        }
    }
}

@Composable
fun Loading() = Text("Loading..")


@Composable
fun Error() = Text("Error on fetching. Please try again.")

@Composable
fun ArticleContent(articles: List<Article>) {
    LazyColumn {
        items(articles) { article ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Title: ${article.title}")
                Text("Description: ${article.description}")
                Text("Publish At: ${article.date}")
                Text("Image url: ${article.imageUrl}")
            }
        }
    }
}

@Composable
fun ArticleSourcesContent(sources: List<Source>) {
    LazyColumn {
        items(sources) { source ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Id: ${source.id}")
                Text("Country: ${source.country}")
                Text("Description: ${source.desc}")
                Text("Name: ${source.name}")
                Text("Language: ${source.language}")
            }
        }
    }
}