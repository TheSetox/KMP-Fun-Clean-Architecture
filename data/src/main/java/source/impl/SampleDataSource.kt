package source.impl

import Platform
import android.os.Build
import source.SampleSource

actual class SampleDataSource : SampleSource {
    actual override fun getPlatform(): Platform {
        val platformName = "Android ${Build.VERSION.SDK_INT}"
        return Platform(platformName)
    }
}