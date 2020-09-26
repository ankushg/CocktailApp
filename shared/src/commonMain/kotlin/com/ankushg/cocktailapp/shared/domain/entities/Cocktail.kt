package com.ankushg.cocktailapp.shared.domain.entities

import com.ankushg.cocktailapp.shared.remote.models.RemoteCocktailSummary
import com.ankushg.cocktailapp.shared.local.Cocktail as LocalCocktail

typealias DomainCocktail = LocalCocktail
typealias DomainCocktailSummary = RemoteCocktailSummary

internal val DomainCocktail.recipeIngredients: List<RecipeIngredient>
    get() = ingredientMeasurePairs
        .mapNotNull { (ingredient, measure) ->
            if (ingredient == null) {
                null
            } else {
                RecipeIngredient(
                    strIngredient = ingredient,
                    strThumbnailUrl = ingredient.asSmallIngredientImageUrl(),
                    strMeasure = measure
                )
            }
        }

val DomainCocktail.strSmallUrl
    get() = "$strDrinkThumb/preview"

private val DomainCocktail.ingredientMeasurePairs: List<Pair<String?, String?>>
    get() = listOfNotNull(
        strIngredient1 to strMeasure1,
        strIngredient2 to strMeasure2,
        strIngredient3 to strMeasure3,
        strIngredient4 to strMeasure4,
        strIngredient5 to strMeasure5,
        strIngredient6 to strMeasure6,
        strIngredient7 to strMeasure7,
        strIngredient8 to strMeasure8,
        strIngredient9 to strMeasure9,
        strIngredient10 to strMeasure10,
        strIngredient11 to strMeasure11,
        strIngredient12 to strMeasure12,
        strIngredient13 to strMeasure13,
        strIngredient14 to strMeasure14,
        strIngredient15 to strMeasure15
    )
