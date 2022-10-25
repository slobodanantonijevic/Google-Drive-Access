package com.sloba.googledriveaccess.ui.composables

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Dialog
import com.sloba.googledriveaccess.R
import com.sloba.googledriveaccess.ui.theme.GoogleDriveTheme
import com.sloba.googledriveaccess.ui.theme.Teal200

/**
 * Loading dialog to display and notify user while app is doing some actual work
 */
@Composable
fun LoadingDialog(
    cornerRadius: Dp = dimensionResource(id = R.dimen.bounds_l),
    paddingStart: Dp = dimensionResource(id = R.dimen.bounds_xxl),
    paddingEnd: Dp = dimensionResource(id = R.dimen.bounds_xxl),
    paddingTop: Dp = dimensionResource(id = R.dimen.bounds_xl),
    paddingBottom: Dp = dimensionResource(id = R.dimen.bounds_xl),
    progressIndicatorColor: Color = Teal200,
    progressIndicatorSize: Dp = dimensionResource(id = R.dimen.bounds_xxxl)
) {

    Dialog(
        onDismissRequest = {
        }
    ) {
        Surface(
            modifier = Modifier.wrapContentSize(),
            elevation = dimensionResource(id = R.dimen.bounds_s),
            shape = RoundedCornerShape(cornerRadius)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = paddingStart, end = paddingEnd, top = paddingTop),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LoadingProgressIndicator(
                    size = progressIndicatorSize,
                    color = progressIndicatorColor
                )

                // Gap between progress indicator and text
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.bounds_xl)))

                // Please wait text
                Text(
                    modifier = Modifier
                        .padding(bottom = paddingBottom),
                    text = "Please wait..."
                )
            }
        }

    }
}

@Composable
fun LoadingProgressIndicator(size: Dp, color: Color) {

    val infiniteTransition = rememberInfiniteTransition()

    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 600
            }
        )
    )

    CircularProgressIndicator(
        progress = 1f,
        modifier = Modifier
            .size(size)
            .rotate(angle)
            .border(
                dimensionResource(id = R.dimen.bounds_l),
                brush = Brush.sweepGradient(
                    listOf(
                        Color.White, // add background color first
                        color.copy(alpha = 0.1f),
                        color
                    )
                ),
                shape = CircleShape
            ),
        strokeWidth = dimensionResource(id = R.dimen.bounds_xxs),
        color = Color.White // Set background color
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewLoadingDialog() {
    GoogleDriveTheme {
        LoadingDialog()
    }
}