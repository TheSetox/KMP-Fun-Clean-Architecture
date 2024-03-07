package data.source

import Platform

interface SampleSource {
    fun getPlatform(): Platform
}