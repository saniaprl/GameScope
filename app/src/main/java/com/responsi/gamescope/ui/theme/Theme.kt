package com.responsi.gamescope.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = DeepNavy,
    onPrimary = SoftWhite,
    primaryContainer = MutedIndigo,
    onPrimaryContainer = SoftLavender,
    secondary = DustyPurple,
    onSecondary = SoftWhite,
    secondaryContainer = PaleLavender,
    onSecondaryContainer = DeepNavy,
    tertiary = SoftPink,
    onTertiary = DeepNavy,
    tertiaryContainer = SoftPink,
    onTertiaryContainer = DeepNavy,
    background = OffWhiteLavender,
    onBackground = DeepNavy,
    surface = SurfaceWhite,
    onSurface = DeepNavy,
    surfaceVariant = LightLavenderGray,
    onSurfaceVariant = MutedSlate,
    outline = LavenderGray,
    error = MutedRed,
    onError = SoftWhite
)

private val DarkColorScheme = darkColorScheme(
    primary = SoftLavender,
    onPrimary = DeepNavy,
    primaryContainer = MutedIndigo,
    onPrimaryContainer = SoftLavender,
    secondary = DustyPurple,
    onSecondary = SoftWhite,
    secondaryContainer = MutedIndigo,
    onSecondaryContainer = SoftLavender,
    tertiary = SoftPink,
    onTertiary = DeepNavy,
    tertiaryContainer = MutedIndigo,
    onTertiaryContainer = SoftPink,
    background = Color(0xFF131628),
    onBackground = SoftWhite,
    surface = Color(0xFF1B1E34),
    onSurface = SoftWhite,
    surfaceVariant = Color(0xFF282C48),
    onSurfaceVariant = LavenderGray,
    outline = MutedSlate,
    error = MutedRed,
    onError = SoftWhite
)

@Composable
fun GameScopeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}