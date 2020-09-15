package com.ankushg.cocktailapp.android.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticAmbientOf

/**
 * Images that can vary by theme.
 */
@Immutable
data class Images(@DrawableRes val icon: Int)

internal val ImageAmbient = staticAmbientOf<Images>()