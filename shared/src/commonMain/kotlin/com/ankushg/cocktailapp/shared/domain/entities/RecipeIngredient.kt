package com.ankushg.cocktailapp.shared.domain.entities

data class RecipeIngredient(
    val strIngredient: String,
    val strThumbnailUrl: String,
    val strMeasure: String? = null
)