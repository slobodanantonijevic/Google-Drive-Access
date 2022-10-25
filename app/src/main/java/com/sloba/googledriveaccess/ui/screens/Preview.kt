package com.sloba.googledriveaccess.ui.screens

import androidx.compose.runtime.Composable
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.sloba.googledriveaccess.app.viewmodel.DriveViewModel

@Composable
fun Preview(viewModel: DriveViewModel) {
    val state = rememberWebViewState(viewModel.preview)

    WebView(
        state = state,
        onCreated = {
            it.settings.javaScriptEnabled = true
            viewModel.setPreview("")
        },
        onDispose = {
            viewModel.setDisplayTabRow(true)
        }
    )
}