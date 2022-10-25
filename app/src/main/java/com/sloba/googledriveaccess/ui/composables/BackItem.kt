package com.sloba.googledriveaccess.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.sloba.googledriveaccess.R
import com.sloba.googledriveaccess.ui.theme.GoogleDriveTheme

/**
 * Composable that we use to move up in folder hierarchy
 */
@Composable
fun BackItem(onClick: (() -> Unit)? = null, title: String) {
    val color = MaterialTheme.colors.onSurface
    Row(modifier = Modifier
        .padding(all = dimensionResource(id = R.dimen.bounds_l))
        .clickable { onClick?.invoke() }
        .fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.bounds_m)),
            tint = color,
            imageVector = Icons.Filled.Undo,
            contentDescription = null
        )

        Text(
            modifier = Modifier,
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBackItem() {
    GoogleDriveTheme {
        BackItem({}, "Compose")
    }
}