package org.example.project.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/*
    Source : https://developer.android.com/develop/ui/compose/designsystems/material3
 */

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        color = high_emphasis,
        fontWeight = FontWeight.SemiBold,
        fontSize = 52.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        color = high_emphasis,
        fontWeight = FontWeight.SemiBold,
        fontSize = 42.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        color = high_emphasis,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        color = high_emphasis,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        color = low_emphasis,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
)