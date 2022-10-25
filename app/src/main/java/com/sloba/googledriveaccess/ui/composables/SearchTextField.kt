package com.sloba.googledriveaccess.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import com.sloba.googledriveaccess.R
import com.sloba.googledriveaccess.app.viewmodel.DriveViewModel
import java.util.*

/**
 * TextField composable for user to enter the search phrase
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    viewModel: DriveViewModel,
    search: MutableState<String>,
    innerStack: Stack<String>
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = search.value,
        onValueChange = {
            search.value = it
        },
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.bounds_l),
                vertical = dimensionResource(id = R.dimen.bounds_m))
            .fillMaxWidth()
            .semantics { contentDescription = "search_field" },
        placeholder = { Text(stringResource(R.string.search)) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (search.value.isNotEmpty()) {
                    keyboardController?.hide()
                    innerStack.clear()
                    innerStack.push("name contains '${search.value}'")
                    viewModel.fetchFiles(innerStack.peek())
                } else {
                    viewModel.clearFiles()
                }

            }
        ),
        maxLines = 1,
        shape = CircleShape,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}