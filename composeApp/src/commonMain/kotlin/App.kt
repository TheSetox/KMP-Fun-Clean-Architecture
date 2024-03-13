import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
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
        Scaffold(
            topBar = TopBar(),
            bottomBar = BottomBar(viewModel),
            content = MainContent(state.value)
        )
    }
}

@Composable
fun MainContent(state: State): @Composable (PaddingValues) -> Unit = {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = it.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is State.ArticleSourceState -> ArticleSourcesContent(state.sources)
            is State.ArticleState -> ArticleContent(state.articles)
            State.Error -> ErrorContent()
            State.Loading -> LoadingContent()
        }
    }
}

@Composable
fun TopBar(): @Composable () -> Unit = {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            text = "Sample Kotlin Multiplatform",
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomBar(viewModel: ViewModel): @Composable () -> Unit = {
    BottomNavigation(modifier = Modifier.fillMaxWidth()) {
        BottomNavigationItem("Articles", viewModel)
        BottomNavigationItem("Sources", viewModel)
    }
}

@Composable
fun LoadingContent() = TextContent("Loading..")


@Composable
fun ErrorContent() = TextContent("Error on fetching. \nPlease try again.")

@Composable
fun TextContent(message: String) =
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

@Composable
fun ArticleItem(article: Article) {
    Card(
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier.padding(8.dp),
        elevation = 16.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ImageItem(article.imageUrl)
            TextTitle(article.title)
            "Description: ".OptionalTextDescription(article.description)
            TextDescription("Publish At: ${article.date}")
        }
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

@Composable
fun ArticleSourceItem(source: Source) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
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
    if (label.isNullOrEmpty().not()) TextDescription("$this $label")
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageItem(imageUrl: String?) {
    val imageItemModifier = Modifier.height(200.dp).fillMaxWidth()
    if (imageUrl.isNullOrEmpty()) Image(
        modifier = imageItemModifier,
        painter = painterResource(Res.drawable.compose_multiplatform),
        contentDescription = null,
    )
    else AsyncImage(
        modifier = imageItemModifier,
        model = imageUrl,
        onLoading = { println("loading") },
        onError = { println(it.result) },
        onSuccess = { println(it.result) },
        contentDescription = null,
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RowScope.BottomNavigationItem(item: String, viewModel: ViewModel) {
    BottomNavigationItem(
        icon = {
            Icon(
                modifier = Modifier.size(50.dp),
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null
            )
        },
        label = { Text(item) },
        selected = true,
        onClick = {
            when (getNavigation(item)) {
                Navigation.Article -> viewModel calls LoadArticles
                Navigation.Sources -> viewModel calls LoadArticleSources
                Navigation.Error -> println("Error loading")
            }
        },
    )
}

fun getNavigation(item: String): Navigation {
    return when (item) {
        "Articles" -> Navigation.Article
        "Sources" -> Navigation.Sources
        else -> Navigation.Error
    }
}

sealed class Navigation {
    data object Article : Navigation()
    data object Sources : Navigation()

    data object Error : Navigation()
}