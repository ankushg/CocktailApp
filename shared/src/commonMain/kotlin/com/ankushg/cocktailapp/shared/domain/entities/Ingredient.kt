package com.ankushg.cocktailapp.shared.domain.entities

import com.ankushg.cocktailapp.shared.local.Ingredient

fun String.asIngredientImageUrl(sizeModifier: String? = null) =
    "https://www.thecocktaildb.com/images/ingredients/${this}${sizeModifier ?: ""}.png"

fun String.asSmallIngredientImageUrl() = asIngredientImageUrl("-Small")

val Ingredient.strImageUrl
    get() = strIngredient.asIngredientImageUrl()

val Ingredient.strSmallImageUrl
    get() = strIngredient.asIngredientImageUrl("-Small")

val Ingredient.strMediumImageUrl
    get() = strIngredient.asIngredientImageUrl("-Medium")
