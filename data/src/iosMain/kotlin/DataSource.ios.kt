import platform.UIKit.UIDevice

actual class DataSource : Source {
    actual override fun getPlatform(): Platform {
        val platformName = UIDevice.currentDevice.systemName() + " " +
                UIDevice.currentDevice.systemVersion
        return Platform(platformName)
    }
}