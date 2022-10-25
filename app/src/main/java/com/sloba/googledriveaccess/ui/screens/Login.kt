package com.sloba.googledriveaccess.ui.screens


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sloba.googledriveaccess.R
import com.sloba.googledriveaccess.ui.composables.PrimaryButton
import com.sloba.googledriveaccess.ui.theme.GoogleDriveTheme
import com.sloba.googledriveaccess.ui.theme.Typography

@Composable
fun Login(onLoginClick: (() -> Unit)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(all = dimensionResource(id = R.dimen.bounds_l)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.drive),
                contentDescription = "logo",
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp))

            Text(
                text = stringResource(R.string.must_log_in),
                style = Typography.driveTypography.h2,
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.bounds_xl))
                    .padding(horizontal = dimensionResource(id = R.dimen.bounds_l)),
                textAlign = TextAlign.Center
            )

            PrimaryButton(
                text = stringResource(id = R.string.log_in),
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.bounds_l),
                        vertical = dimensionResource(id = R.dimen.bounds_xl)
                    )
                    .semantics { contentDescription = "login_button" }
            )
        }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLogin() {
    GoogleDriveTheme {
        Surface {
            Login { }
        }
    }
}