package com.CepteSaham.CepteSaham.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.CepteSaham.CepteSaham.R

// Set of Material typography styles to start with
val BigShoulderFamily = FontFamily(
    Font(R.font.big_shouders_display_bold, FontWeight.Bold),
    Font(R.font.big_shouders_display_extrabold, FontWeight.ExtraBold),
    Font(R.font.big_shouders_display_regular, FontWeight.Normal),
    Font(R.font.big_shouders_display_ligth, FontWeight.Light),
    Font(R.font.big_shouders_display_medium, FontWeight.Medium),
    Font(R.font.big_shoulders_display_black, FontWeight.Black),
    Font(R.font.big_shouders_display_semibold, FontWeight.SemiBold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = BigShoulderFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )


    // You can customize other text styles here

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

@Composable
fun CepteSahamTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = Typography,
        // Provide other theme attributes here
        content = content
    )
}