package data.source.impl

import Platform
import data.source.SampleSource

actual class SampleDataSource : SampleSource {
    actual override fun getPlatform(): Platform {
//        val platformName = "Java ${System.getProperty("java.version")}"
        return Platform("")
    }
}