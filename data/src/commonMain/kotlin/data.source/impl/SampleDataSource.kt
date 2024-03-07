package data.source.impl

import Platform
import data.source.SampleSource

expect class SampleDataSource(): SampleSource {
    override fun getPlatform(): Platform

}