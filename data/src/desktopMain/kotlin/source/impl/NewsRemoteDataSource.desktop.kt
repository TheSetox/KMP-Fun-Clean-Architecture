package source.impl

actual fun getApiKey(): String {
    return System.getenv("NEWS_API_KEY")
}