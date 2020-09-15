package com.ankushg.cocktailapp.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.ankushg.cocktailapp.android.ui.MainAppView
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailAppTheme {
                MainAppView(onBackPressedDispatcher)
            }
        }
    }
}