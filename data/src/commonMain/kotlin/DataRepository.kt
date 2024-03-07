import data.source.SampleSource

class DataRepository(private val sampleSource: SampleSource): Repository {
    override fun getPlatform() = sampleSource.getPlatform()
}