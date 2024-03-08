package org.example.project

import App
import MyUseCase
import Repository
import ViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.android.ext.android.inject
import source.LocalSource
import source.NewsRemoteSource
import source.SampleSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sampleSource: SampleSource by inject()
        val repository: Repository by inject()
        val localSource: LocalSource by inject()
        val newsRemoteSource: NewsRemoteSource by inject()
        val myUseCase: MyUseCase by inject()
        val viewModel: ViewModel by inject()
        setContent {
            App(
                sampleSource,
                repository,
                localSource,
                newsRemoteSource,
                myUseCase,
                viewModel
            )
        }
    }
}