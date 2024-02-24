package com.him.sama.spotifycompose.common.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.him.sama.spotifycompose.common.ui.R

val SpFontFamily = FontFamily(
    Font(R.font.circular_sp_book, FontWeight.Light),
    Font(R.font.circular_sp_book, FontWeight.Medium),
    Font(R.font.circular_sp_book, FontWeight.SemiBold),
    Font(R.font.circular_sp_bold, FontWeight.Bold)
)
val SpTitleFontFamily = FontFamily(
    Font(R.font.circular_sp_title_black, FontWeight.Light),
    Font(R.font.circular_sp_title_black, FontWeight.Medium),
    Font(R.font.circular_sp_title_black, FontWeight.SemiBold),
    Font(R.font.circular_sp_title_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = SpFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    titleSmall = TextStyle(
        fontFamily = SpTitleFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SpTitleFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.2.sp
    ),
    titleLarge = TextStyle(
        fontFamily = SpTitleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.0.sp
    ),
)
