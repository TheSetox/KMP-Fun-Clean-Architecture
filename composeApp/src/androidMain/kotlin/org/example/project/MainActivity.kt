package org.example.project

import App
import ViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel : ViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(viewModel)
        }
    }

    @Preview
    @Composable
    fun AppAndroidPreview() {
        App(viewModel)
    }
}
