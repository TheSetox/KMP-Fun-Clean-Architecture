package source.impl

import Platform
import source.SampleSource

actual class SampleDataSource : SampleSource {
    actual override fun getPlatform(): Platform {
//        val platformName = "Java ${System.getProperty("java.version")}"
        return Platform("")
    }
}