package data.source.impl

import data.source.SampleSource

actual class SampleDataSource : SampleSource {
    actual override fun getPlatform(): Platform {
        val platformName = UIDevice.currentDevice.systemName() + " " +
                UIDevice.currentDevice.systemVersion
        return Platform(platformName)
    }

}