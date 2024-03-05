import android.os.Build

actual class DataSource : Source {
    actual override fun getPlatform(): Platform {
        val platformName = "Android ${Build.VERSION.SDK_INT}"
        return Platform(platformName)
    }
}