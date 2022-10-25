package com.sloba.googledriveaccess.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sloba.googledriveaccess.R
import com.sloba.googledriveaccess.ui.theme.GoogleDriveTheme
import com.sloba.googledriveaccess.ui.theme.Teal200

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, enabled: Boolean = true, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(minHeight = dimensionResource(id = R.dimen.button_height)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_radius)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = colorResource(id = R.color.dark_gray),
            disabledBackgroundColor = colorResource(id = R.color.color_grey_transparent)
        ),
        enabled = enabled,
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewPrimaryButton() {
    GoogleDriveTheme {
        Surface {
            PrimaryButton(
                text = stringResource(id = R.string.log_in),
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.bounds_l),
                        vertical = dimensionResource(id = R.dimen.bounds_xl)
                    )
            )
        }
    }
}