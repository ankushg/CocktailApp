package com.ankushg.cocktailapp.android.ui

import CocktailDescription
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.ankushg.cocktailapp.android.ui.category.CategoryList
import com.ankushg.cocktailapp.android.ui.cocktail.CocktailList
import com.ankushg.cocktailapp.android.ui.ingredient.IngredientDescription
import com.ankushg.cocktailapp.android.ui.utils.BackButtonHandler
import com.ankushg.cocktailapp.android.ui.utils.ProvideDisplayInsets
import com.ankushg.cocktailapp.shared.app.Action
import com.ankushg.cocktailapp.shared.app.AppViewModel
import com.ankushg.cocktailapp.shared.app.ViewState
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@ExperimentalCoroutinesApi
fun MainAppView(appViewModel: AppViewModel) {
    val appState by appViewModel.stateFlow.collectAsState()
    val actionRouter = remember(appViewModel) { ActionRouter(appViewModel) }

    ProvideDisplayInsets {
        Surface(color = MaterialTheme.colors.background) {
            Crossfade(appState) { state ->
                when (state) {
                    is ViewState.IngredientDetails -> IngredientDescription(
                        state = state,
                        onUpPressed = actionRouter.onUpPressed
                    )
                    is ViewState.CategoryList -> CategoryList(
                        state = state,
                        onCategoryClicked = actionRouter.onCategoryClicked
                    )
                    is ViewState.CocktailList -> CocktailList(
                        state = state,
                        onDrinkClicked = actionRouter.onDrinkClicked
                    )
                    is ViewState.DrinkDetails -> CocktailDescription(
                        state = state,
                        onIngredientClicked = actionRouter.onIngredientClicked,
                        onUpPressed = actionRouter.onUpPressed
                    )
                }
            }
        }
    }

    BackButtonHandler(onBackPressed = actionRouter.onBackPressed)
}

private class ActionRouter(val appViewModel: AppViewModel) {
    val onCategoryClicked = { category: DrinkCategory ->
        appViewModel.submitAction(Action.SelectCategory(category))
    }

    val onDrinkClicked = { drinkId: Long ->
        appViewModel.submitAction(Action.SelectDrink(drinkId))
    }

    val onIngredientClicked = { ingredientId: String ->
        appViewModel.submitAction(Action.SelectIngredient(ingredientId))
    }

    val onUpPressed = { appViewModel.submitAction(Action.UpPress) }
    val onBackPressed = { appViewModel.submitAction(Action.BackPress) }
}