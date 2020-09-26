package com.ankushg.cocktailapp.shared.remote.models

import com.ankushg.cocktailapp.shared.domain.entities.DomainIngredient
import kotlinx.serialization.Serializable

@Serializable
data class RemoteIngredient(
    val strIngredient: String,
    val idIngredient: Long? = null,
    val strDescription: String? = null,
    val strType: String? = null,
    val strAlcohol: String? = null,
    val strABV: Long? = null
)

fun RemoteIngredient.toDomainIngredient(): DomainIngredient = DomainIngredient(
    strIngredient = strIngredient,
    idIngredient = idIngredient,
    strDescription = strDescription,
    strType = strType,
    strAlcohol = strAlcohol,
    strABV = strABV
)

/**
 * TheCocktailDB's weird lightweight representation of an Ingredient.
 *
 * The API uses a different variable name ([strIngredient1]) than the name of the field on the
 * detailed model response ([Ingredient.strIngredient])
 */
@Serializable
data class RemoteIngredientSummary(val strIngredient1: String)

fun RemoteIngredientSummary.toDomainIngredient() =
    RemoteIngredient(strIngredient = strIngredient1).toDomainIngredient()

@Serializable
data class IngredientNameResponse(val ingredients: List<RemoteIngredientSummary>)

@Serializable
data class IngredientDetailResponse(val ingredients: List<RemoteIngredient>)
