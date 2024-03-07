package source.impl

import org.example.project.shared.BuildConfig

actual fun getApiKey(): String {
    return BuildConfig.key
}