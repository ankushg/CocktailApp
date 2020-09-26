package com.ankushg.cocktailapp.shared.domain.entities

data class CocktailWithRecipe(
    val cocktail: DomainCocktail,
    val recipeIngredients: List<RecipeIngredient>
)