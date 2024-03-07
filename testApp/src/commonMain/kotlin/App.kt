import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.source.LocalSource
import data.source.SampleSource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(sampleSource: SampleSource, repository: Repository, localSource: LocalSource) {
    MaterialTheme {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sources: ${localSource.getSources()}")
            Text("Source Platform: ${sampleSource.getPlatform()}")
            Text("Articles: ${localSource.getArticles()}")
            Text("Repository Platform: ${repository.getPlatform()}")
        }
    }
}