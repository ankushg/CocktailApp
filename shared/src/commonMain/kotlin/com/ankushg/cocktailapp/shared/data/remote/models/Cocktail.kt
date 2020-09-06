@file:UseSerializers(
    CocktailSummarySerializer::class,
    CocktailSerializer::class,
)

package com.ankushg.cocktailapp.shared.data.remote.models

import com.ankushg.cocktailapp.shared.data.local.Cocktail
import com.ankushg.cocktailapp.shared.data.local.SelectSummaryById
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.UseSerializers

/**
 * Temporarily using kotlinx.serialization's experimental serializer generator for external classes
 */
@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = SelectSummaryById::class)
object CocktailSummarySerializer

/**
 * Temporarily using kotlinx.serialization's experimental serializer generator for external classes
 */
@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Cocktail::class)
object CocktailSerializer

@Serializable
data class CocktailSummaryResponse(val drinks: List<SelectSummaryById>)

@Serializable
data class CocktailDetailResponse(val drinks: List<Cocktail>)