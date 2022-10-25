package com.sloba.googledriveaccess.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sloba.googledriveaccess.R
import com.sloba.googledriveaccess.app.viewmodel.DriveViewModel
import com.sloba.googledriveaccess.ui.composables.DriveList
import com.sloba.googledriveaccess.ui.composables.SearchTextField
import java.util.*

/**
 * Composable for Search screen
 */
@Composable
fun Search(viewModel: DriveViewModel) {
    val innerStack by rememberSaveable { mutableStateOf(Stack<String>()) }
    val search = rememberSaveable { mutableStateOf("")}

    LaunchedEffect(Unit, block = {
        viewModel.clearFiles()
        if (innerStack.isNotEmpty() && search.value.isNotEmpty()) {
            viewModel.fetchFiles(innerStack.peek())
        }
    })

    Column (Modifier.fillMaxSize()) {
        SearchTextField(viewModel, search, innerStack)
        DriveList(viewModel, innerStack, stringResource(id = R.string.search))
    }
}