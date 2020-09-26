package com.ankushg.cocktailapp.shared.domain.entities

import com.ankushg.cocktailapp.shared.local.Ingredient as LocalIngredient

typealias DomainIngredient = LocalIngredient

val DomainIngredient.strImageUrl
    get() = strIngredient.asIngredientImageUrl()

val DomainIngredient.strSmallImageUrl
    get() = strIngredient.asIngredientImageUrl("-Small")

val DomainIngredient.strMediumImageUrl
    get() = strIngredient.asIngredientImageUrl("-Medium")

private fun String.asIngredientImageUrl(sizeModifier: String? = null) =
    "https://www.thecocktaildb.com/images/ingredients/${this}${sizeModifier ?: ""}.png"

internal fun String.asSmallIngredientImageUrl() = asIngredientImageUrl("-Small")

