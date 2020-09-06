@file:UseSerializers(
    IngredientSerializer::class
)

package com.ankushg.cocktailapp.shared.data.remote.models

import com.ankushg.cocktailapp.shared.data.local.Ingredient
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.UseSerializers

/**
 * Temporarily using kotlinx.serialization's experimental serializer generator for external classes
 */
@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Ingredient::class)
object IngredientSerializer

/**
 * Represents TheCocktailDB's weird lightweight representation of an Ingredient, where it uses a
 * different variable name from the actual ID column (unlike Cocktail)
 */
@Serializable
data class IngredientSummary(
    val strIngredient1: String
)

@Serializable
data class IngredientNameResponse(val ingredients: List<IngredientSummary>)

@Serializable
data class IngredientDetailResponse(val ingredients: List<Ingredient>)