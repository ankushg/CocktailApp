package com.ankushg.cocktailapp.shared.app

import com.ankushg.cocktailapp.shared.domain.entities.CocktailWithRecipe
import com.ankushg.cocktailapp.shared.domain.entities.DomainCocktail
import com.ankushg.cocktailapp.shared.domain.entities.DomainIngredient
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory

sealed class ViewState {
    data class CategoryList(
        val categories: List<DrinkCategory>
    ) : ViewState()

    data class CocktailList(
        val cocktails: List<DomainCocktail>
    ) : ViewState()

    data class DrinkDetails(
        val cocktailWithRecipe: CocktailWithRecipe
    ) : ViewState()

    data class IngredientDetails(
        val ingredient: DomainIngredient,
        val usedInCocktails: List<DomainCocktail>
    ) : ViewState()
}