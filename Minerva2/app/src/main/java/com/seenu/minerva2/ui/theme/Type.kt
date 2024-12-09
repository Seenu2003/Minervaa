package com.seenu.minerva2.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.seenu.minerva2.R

// Set of Material typography styles to start with
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val playfairDisplay = FontFamily(
    Font(R.font.playfairdisplay_regular, FontWeight.Normal),
    Font(R.font.playfairdisplay_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.playfairdisplay_medium, FontWeight.Medium),
    Font(R.font.playfairdisplay_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.playfairdisplay_semibold, FontWeight.SemiBold),
    Font(R.font.playfairdisplay_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.playfairdisplay_bold, FontWeight.Bold),
    Font(R.font.playfairdisplay_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.playfairdisplay_extrabold, FontWeight.ExtraBold),
    Font(R.font.playfairdisplay_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.playfairdisplay_black, FontWeight.Black),
    Font(R.font.playfairdisplay_blackitalic, FontWeight.Black, FontStyle.Italic)
)

val hankenGrotesk = FontFamily(
    Font(R.font.hankengrotesk_thin, FontWeight.Thin),
    Font(R.font.hankengrotesk_thinitalic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.hankengrotesk_extralight, FontWeight.ExtraLight),
    Font(R.font.hankengrotesk_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.hankengrotesk_light, FontWeight.Light),
    Font(R.font.hankengrotesk_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.hankengrotesk_regular, FontWeight.Normal),
    Font(R.font.hankengrotesk_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.hankengrotesk_medium, FontWeight.Medium),
    Font(R.font.hankengrotesk_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.hankengrotesk_semibold, FontWeight.SemiBold),
    Font(R.font.hankengrotesk_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.hankengrotesk_bold, FontWeight.Bold),
    Font(R.font.hankengrotesk_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.hankengrotesk_extrabold, FontWeight.ExtraBold),
    Font(R.font.hankengrotesk_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.hankengroteskblack, FontWeight.Black),
    Font(R.font.hankengroteskblackitalic, FontWeight.Black, FontStyle.Italic)
)

val MinervaTypography = Typography(
    // Use Playfair for display styles
    displayLarge = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 56.sp,
        lineHeight = 64.sp
    ),
    displayMedium = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 44.sp,
        lineHeight = 52.sp
    ),
    displaySmall = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        lineHeight = 44.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        lineHeight = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = hankenGrotesk,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
        lineHeight = 23.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = hankenGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 24.sp
    ),
    bodySmall = TextStyle(
        fontFamily = hankenGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp
    ),
    labelLarge = TextStyle(
        fontFamily = hankenGrotesk,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = hankenGrotesk,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = hankenGrotesk,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp
    )
)




