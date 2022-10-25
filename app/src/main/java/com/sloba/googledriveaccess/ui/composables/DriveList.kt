package com.sloba.googledriveaccess.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.sloba.googledriveaccess.app.util.Constants.TYPE_GOOGLE_DRIVE_FOLDER
import com.sloba.googledriveaccess.app.viewmodel.DriveViewModel
import java.util.*

/**
 * Template screen for list of Files on all pages in the main navigation graph
 */
@Composable
fun DriveList
            (viewModel: DriveViewModel,
             innerStack: Stack<String>,
             desc: String = "") {
    val title = rememberSaveable { mutableStateOf("") }
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = desc }
    ) {

        if (innerStack.size > 1) {
            item {
                BackItem (
                    onClick = {
                        title.value = title.value.substringBeforeLast("/")
                        innerStack.pop()
                        viewModel.fetchFiles(innerStack.peek())
                    },
                    title =  title.value)
            }
        }

        items(viewModel.files) { file ->
            var onClick: (() -> Unit)? = null
            if (file.mimeType == TYPE_GOOGLE_DRIVE_FOLDER) {
                onClick = {
                    innerStack.push("'${file.id}' in parents")
                    title.value += "/ ${file.name} "
                    viewModel.fetchFiles(innerStack.peek())
                }
            }
            FileItem(
                viewModel = viewModel,
                file = file,
                onClick = onClick)
        }.also {
            viewModel.setLoading(false)
        }
    }
}