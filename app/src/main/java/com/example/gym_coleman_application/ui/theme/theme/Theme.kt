package com.example.gym_coleman_application.ui.theme.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// --- Paleta para Modo Oscuro usando nuestros nuevos colores ---
private val DarkColorScheme = darkColorScheme(
    primary = VividRed,            // Color principal para acciones (botones, etc.)
    onPrimary = Color.White,       // Color del texto sobre el color primario
    secondary = LightGray,         // Color secundario (para filtros, chips, etc.)
    onSecondary = RichBlack,       // Texto sobre el color secundario
    background = DarkJungleGreen,  // Fondo general de la app
    onBackground = LightGray,      // Texto sobre el fondo general
    surface = RichBlack,           // Fondo para superficies (tarjetas, menús)
    onSurface = LightGray,         // Texto sobre las superficies
    error = VividRed,              // Color para errores
    onError = Color.White
)

// --- Paleta para Modo Claro usando nuestros nuevos colores ---
private val LightColorScheme = lightColorScheme(
    primary = FireEngineRed,       // Color principal
    onPrimary = Color.White,       // Texto sobre el primario
    secondary = DarkGray,          // Color secundario
    onSecondary = Color.White,     // Texto sobre el secundario
    background = Alabaster,        // Fondo general
    onBackground = Charcoal,       // Texto sobre el fondo
    surface = White,               // Fondo para superficies
    onSurface = Charcoal,          // Texto sobre las superficies
    error = FireEngineRed,         // Color para errores
    onError = Color.White
)

@Composable
fun GymColemanApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // El theming dinámico (Android 12+) no es ideal para una identidad de marca fuerte, lo desactivamos.
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
