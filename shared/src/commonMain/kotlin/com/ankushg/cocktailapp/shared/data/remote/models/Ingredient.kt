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
 * Using kotlinx.serialization's serializer generator on the SqlDelight generated class
 *
 * Ideally would be a separate, isolated, mapped data class
 */
@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Ingredient::class)
object IngredientSerializer

/**
 * TheCocktailDB's weird lightweight representation of an Ingredient.
 *
 * The API uses a different variable name ([strIngredient1]) than the name of the field on the
 * detailed model response ([Ingredient.strIngredient])
 */
@Serializable
data class IngredientSummary(val strIngredient1: String)

@Serializable
data class IngredientNameResponse(val ingredients: List<IngredientSummary>)

@Serializable
data class IngredientDetailResponse(val ingredients: List<Ingredient>)