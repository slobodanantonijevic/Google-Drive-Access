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
 * Composable for My Drive screen
 */
@Composable
fun Browse(viewModel: DriveViewModel) {
    val innerStack by rememberSaveable { mutableStateOf(Stack<String>()) }
    LaunchedEffect(Unit, block = {
        if (innerStack.empty()) {
            innerStack.push("'root' in parents")
        }
        viewModel.fetchFiles(innerStack.peek())
    })
    DriveList(viewModel, innerStack, stringResource(id = R.string.my_drive))
}