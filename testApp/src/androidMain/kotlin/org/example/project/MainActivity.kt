package org.example.project

import App
import Repository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import data.source.LocalSource
import data.source.SampleSource
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sampleSource: SampleSource by inject()
        val repository: Repository by inject()
        val localSource: LocalSource by inject()
        setContent {
            App(sampleSource, repository, localSource)
        }
    }
}