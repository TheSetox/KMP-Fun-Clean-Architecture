package source.impl

import Platform
import source.SampleSource

expect class SampleDataSource(): SampleSource {
    override fun getPlatform(): Platform

}