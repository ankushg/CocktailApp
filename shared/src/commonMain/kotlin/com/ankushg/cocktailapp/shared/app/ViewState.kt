package com.ankushg.cocktailapp.shared.app

import com.ankushg.cocktailapp.shared.domain.entities.RecipeIngredient
import com.ankushg.cocktailapp.shared.local.Cocktail
import com.ankushg.cocktailapp.shared.local.Ingredient
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory

sealed class ViewState {
    data class CategoryList(
        val categories: List<DrinkCategory>
    ) : ViewState()

    data class CocktailList(
        val cocktails: List<Cocktail>
    ) : ViewState()

    data class DrinkDetails(
        val cocktail: Cocktail,
        val recipeIngredients: List<RecipeIngredient>
    ) : ViewState()

    data class IngredientDetails(
        val ingredient: Ingredient,
        val usedInCocktails: List<Cocktail>
    ) : ViewState()
}