package com.ankushg.cocktailapp.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.unit.dp
import com.ankushg.cocktailapp.android.R

private val DarkColorPalette = darkColors(
    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200
)

private val LightColorPalette = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val LightElevation = Elevation()
private val DarkElevation = Elevation(card = 1.dp)

private val LightImages = Images(icon = R.drawable.ic_launcher_foreground)
private val DarkImages = Images(icon = R.drawable.ic_launcher_foreground)

@Composable
fun CocktailAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val elevation = if (darkTheme) DarkElevation else LightElevation
    val images = if (darkTheme) DarkImages else LightImages
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    Providers(
        ElevationAmbient provides elevation,
        ImageAmbient provides images
    ) {
        MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

/**
 * Alternate to [MaterialTheme] allowing us to add our own theme systems (e.g. [Elevation]) or to
 * extend [MaterialTheme]'s types e.g. return our own [Colors] extension
 */
object CocktailAppTheme {
    /**
     * Proxy to [MaterialTheme]
     */
    @Composable
    val colors: Colors
        get() = MaterialTheme.colors

    /**
     * Proxy to [MaterialTheme]
     */
    @Composable
    val typography: Typography
        get() = MaterialTheme.typography

    /**
     * Proxy to [MaterialTheme]
     */
    @Composable
    val shapes: Shapes
        get() = MaterialTheme.shapes

    /**
     * Retrieves the current [Elevation] at the call site's position in the hierarchy.
     */
    @Composable
    val elevations: Elevation
        get() = ElevationAmbient.current

    /**
     * Retrieves the current [Images] at the call site's position in the hierarchy.
     */
    @Composable
    val images: Images
        get() = ImageAmbient.current
}