package com.sloba.googledriveaccess.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.sloba.googledriveaccess.R
import com.sloba.googledriveaccess.app.viewmodel.DriveViewModel
import com.sloba.googledriveaccess.ui.composables.DriveList
import java.util.*

/**
 * Composable for the Shared with me screen
 */
@Composable
fun Shared(viewModel: DriveViewModel) {
    val innerStack by rememberSaveable { mutableStateOf(Stack<String>()) }
    LaunchedEffect(Unit, block = {
        if (innerStack.empty()) {
            innerStack.push("sharedWithMe")
        }
        viewModel.fetchFiles(innerStack.peek())
    })
    DriveList(viewModel, innerStack, stringResource(id = R.string.shared_with_me))
}