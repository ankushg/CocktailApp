package com.ankushg.cocktailapp.android.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Elevation values that can be themed.
 */
@Immutable
data class Elevation(val card: Dp = 0.dp)

internal val ElevationAmbient = staticAmbientOf { Elevation() }