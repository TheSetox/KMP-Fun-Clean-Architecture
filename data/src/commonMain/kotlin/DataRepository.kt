class DataRepository(
    private val source: Source = DataSource()
): Repository {
    override fun getPlatform() = source.getPlatform()
}