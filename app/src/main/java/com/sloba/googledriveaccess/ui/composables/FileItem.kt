package com.sloba.googledriveaccess.ui.composables

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.sloba.googledriveaccess.R
import com.sloba.googledriveaccess.app.model.DriveFile
import com.sloba.googledriveaccess.app.util.Constants.DRIVE_PREVIEW_URL
import com.sloba.googledriveaccess.app.util.Constants.TYPE_AUDIO
import com.sloba.googledriveaccess.app.util.Constants.TYPE_GOOGLE_DOC
import com.sloba.googledriveaccess.app.util.Constants.TYPE_GOOGLE_DRAWING
import com.sloba.googledriveaccess.app.util.Constants.TYPE_GOOGLE_DRIVE_FOLDER
import com.sloba.googledriveaccess.app.util.Constants.TYPE_GOOGLE_FORM
import com.sloba.googledriveaccess.app.util.Constants.TYPE_GOOGLE_MAP
import com.sloba.googledriveaccess.app.util.Constants.TYPE_GOOGLE_SHEET
import com.sloba.googledriveaccess.app.util.Constants.TYPE_IMAGE_JPEG
import com.sloba.googledriveaccess.app.util.Constants.TYPE_IMAGE_JPG
import com.sloba.googledriveaccess.app.util.Constants.TYPE_IMAGE_PNG
import com.sloba.googledriveaccess.app.util.Constants.TYPE_PHOTO
import com.sloba.googledriveaccess.app.util.Constants.TYPE_VIDEO
import com.sloba.googledriveaccess.app.viewmodel.DriveViewModel
import com.sloba.googledriveaccess.ui.theme.GoogleDriveTheme
import com.sloba.googledriveaccess.ui.theme.Teal300

/**
 * File list item composable for My Drive, Shared with me and Search screens
 */
@Composable
fun FileItem(viewModel: DriveViewModel?, file: DriveFile, onClick: (() -> Unit)? = null) {
    val color = MaterialTheme.colors.onSurface
    var expanded by remember { mutableStateOf(false) }
    GoogleDriveTheme {
        Box (
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(id = R.dimen.bounds_xs),
                    horizontal = dimensionResource(id = R.dimen.bounds_s)
                )
                .background(color = Teal300)) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = dimensionResource(id = R.dimen.bounds_m))
                    .animateContentSize()
                    .clickable {
                        if (onClick != null) {
                            onClick.invoke()
                        } else {
                            expanded = !expanded
                        }
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.bounds_m)),
                        tint = color,
                        imageVector = getIcon(file),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier,
                        text = file.name,
                        maxLines = if (!expanded) 1 else Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis
                    )
                }


                if (expanded) {
                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.bounds_l),
                                vertical = dimensionResource(id = R.dimen.bounds_xl)
                            ),
                        text = stringResource(id = R.string.preview),
                        onClick = {
                            viewModel?.setDisplayTabRow(false)
                            viewModel?.setPreview(String.format(DRIVE_PREVIEW_URL, file.id))
                        })
                }
            }
        }
    }
}

fun getIcon(file: DriveFile): ImageVector {
    return when (file.mimeType) {
        TYPE_AUDIO -> Icons.Filled.AudioFile
        TYPE_GOOGLE_DOC -> Icons.Filled.Description
        TYPE_GOOGLE_DRAWING -> Icons.Filled.Draw
        TYPE_GOOGLE_DRIVE_FOLDER -> Icons.Filled.Folder
        TYPE_GOOGLE_FORM -> Icons.Filled.Assignment
        TYPE_GOOGLE_MAP -> Icons.Filled.Map
        TYPE_IMAGE_PNG,
        TYPE_IMAGE_JPG,
        TYPE_IMAGE_JPEG,
        TYPE_PHOTO -> Icons.Filled.Photo
        TYPE_GOOGLE_SHEET -> Icons.Filled.BorderOuter
        TYPE_VIDEO -> Icons.Filled.VideoFile
        else -> Icons.Filled.InsertDriveFile
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewFileItemFile() {
    GoogleDriveTheme {
        Surface {
            FileItem(
                null,
                file = DriveFile(
                    "",
                    "",
                    "Lorem ipsum dolor sit amet non galisum accusamus qui cupidit",
                    TYPE_GOOGLE_DOC
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewFileItemFolder() {
    GoogleDriveTheme {
        Surface {
            FileItem(
                null,
                file = DriveFile(
                    "",
                    "",
                    "Some folder",
                    TYPE_GOOGLE_DRIVE_FOLDER
                )
            )
        }
    }
}