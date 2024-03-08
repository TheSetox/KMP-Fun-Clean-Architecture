import kotlinx.coroutines.CoroutineScope

@Suppress("expect-actual-classes")
expect open class BaseViewModel() {
    val scope: CoroutineScope
}