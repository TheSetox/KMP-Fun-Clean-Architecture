actual class DataSource : Source {
    actual override fun getPlatform(): Platform {
        val platformName = "Web with Kotlin/Wasm"
        return Platform(platformName)
    }
}