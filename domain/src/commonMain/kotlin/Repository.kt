

data class Platform(
    val name: String
)

interface Repository {
    fun getPlatform(): Platform
}