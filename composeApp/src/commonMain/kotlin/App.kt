import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import entity.Article
import entity.Source
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import samplemultiplatform.composeapp.generated.resources.Res
import samplemultiplatform.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(viewModel: ViewModel) {
    val state = viewModel.state.collectAsState()
    viewModel calls LoadArticles


    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (val stateValue = state.value) {
                is State.ArticleSourceState -> ArticleSourcesContent(stateValue.sources)
                is State.ArticleState -> ArticleContent(stateValue.articles)
                State.Error -> ErrorScreen()
                State.Loading -> LoadingScreen()
            }
        }
    }
}

@Composable
fun LoadingScreen() = TextScreen("Loading..")


@Composable
fun ErrorScreen() = TextScreen("Error on fetching. \nPlease try again.")

@Composable
fun TextScreen(message: String) =
    Text(
        modifier = Modifier
            .wrapContentWidth()
            .padding(20.dp),
        text = message,
        fontSize = 30.sp,
        lineHeight = 60.sp,
    )

@Composable
fun ArticleContent(articles: List<Article>) = LazyColumn {
    items(articles) {
        ArticleItem(it)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ArticleItem(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
        )
        TextTitle(article.title)
        "Description: ".OptionalTextDescription(article.description)
        TextDescription("Publish At: ${article.date}")
        "Image url: ".OptionalTextDescription(article.imageUrl)
    }
}

@Composable
fun ArticleSourcesContent(sources: List<Source>) = LazyColumn(
    contentPadding = PaddingValues(20.dp)
) {
    items(sources) {
        ArticleSourceItem(it)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ArticleSourceItem(source: Source) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
        )
        TextTitle(source.id)
        TextDescription("Country: ${source.country}")
        TextDescription("Description: ${source.desc}")
        TextDescription("Name: ${source.name}")
        TextDescription("Language: ${source.language}")
    }
}

@Composable
fun TextTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun TextDescription(label: String) {
    Text(label)
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun String.OptionalTextDescription(label: String?) {
    if (label != null) TextDescription("$this $label")
}