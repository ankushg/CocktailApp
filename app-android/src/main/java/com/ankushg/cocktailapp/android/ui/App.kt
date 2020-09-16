package com.ankushg.cocktailapp.android.ui

import CocktailDescription
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import com.ankushg.cocktailapp.android.placeholders.cocktails.RecipeIngredient
import com.ankushg.cocktailapp.android.placeholders.cocktails.allDrinkCategories
import com.ankushg.cocktailapp.android.placeholders.cocktails.cocktailSummaries
import com.ankushg.cocktailapp.android.ui.category.CategoryList
import com.ankushg.cocktailapp.android.ui.cocktail.CocktailList
import com.ankushg.cocktailapp.android.ui.ingredient.IngredientDescription
import com.ankushg.cocktailapp.android.ui.utils.BackDispatcherAmbient
import com.ankushg.cocktailapp.android.ui.utils.Navigator
import com.ankushg.cocktailapp.android.ui.utils.ProvideDisplayInsets
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.local.Cocktail
import com.ankushg.cocktailapp.shared.local.Ingredient

@Composable
fun MainAppView(backDispatcher: OnBackPressedDispatcher) {
    @Suppress("RemoveExplicitTypeArguments")
    val navigator: Navigator<Destination> = rememberSavedInstanceState(
        saver = Navigator.saver<Destination>(backDispatcher)
    ) {
        Navigator(Destination.CategoryList, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }

    Providers(BackDispatcherAmbient provides backDispatcher) {
        ProvideDisplayInsets {
            Surface(color = MaterialTheme.colors.background) {
                Crossfade(navigator.current) { destination ->
                    when (destination) {
                        Destination.CategoryList -> CategoryList(
                            categories = allDrinkCategories,
                            onClick = actions.selectCategory
                        )
                        is Destination.DrinksByCategory -> CocktailList(
                            cocktails = cocktailSummaries,
                            onClick = { actions.selectDrink(it.idDrink) }
                        )
                        is Destination.DrinkDetails -> CocktailDescription(
                            idDrink = destination.idDrink,
                            selectIngredient = actions.selectIngredient,
                            upPress = actions.upPress
                        )
                        is Destination.IngredientDetails -> IngredientDescription(
                            strIngredient = destination.strIngredient,
                            upPress = actions.upPress
                        )
                    }
                }
            }
        }
    }
}