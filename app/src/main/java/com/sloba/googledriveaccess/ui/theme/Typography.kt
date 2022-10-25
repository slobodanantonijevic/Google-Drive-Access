package com.sloba.googledriveaccess.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sloba.googledriveaccess.R

object Typography {

    private val medium =
        Font(
            R.font.circular_std_medium,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        )
    private val book =
        Font(
            R.font.circular_std_book,
            weight = FontWeight.Light,
            style = FontStyle.Normal
        )
    private val bold =
        Font(
            R.font.circular_std_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        )

    private val fonts = FontFamily(medium, book, bold)

    val driveTypography = androidx.compose.material.Typography(
        h1 = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        ),

        h2 = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),

        body1 = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp
        ),

        body2 = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
        ),

        caption = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
        ),

        button = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            letterSpacing = 0.sp
        ),

        subtitle2 = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.sp
        ),

        h5 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 10.sp,
            letterSpacing = 0.sp
        )
    )
}