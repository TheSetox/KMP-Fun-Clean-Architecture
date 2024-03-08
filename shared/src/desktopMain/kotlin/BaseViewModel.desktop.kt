import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

actual open class BaseViewModel {
    actual val scope: CoroutineScope
        get() = CoroutineScope(Dispatchers.IO)
}