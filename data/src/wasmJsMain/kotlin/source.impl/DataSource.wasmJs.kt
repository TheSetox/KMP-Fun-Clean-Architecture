actual class SampleDataSource : Source {
    actual override fun getPlatform(): Platform {
        val platformName = "Web with Kotlin/Wasm"
        return Platform(platformName)
    }
}