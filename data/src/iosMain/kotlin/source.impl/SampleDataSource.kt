package source.impl

import platform.UIKit.UIDevice
import Platform
import source.SampleSource

actual class SampleDataSource : SampleSource {
    actual override fun getPlatform(): Platform {
        val platformName = UIDevice.currentDevice.systemName() + " " +
                UIDevice.currentDevice.systemVersion
        return Platform(platformName)
    }

}