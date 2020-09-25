@file:UseSerializers(
    CocktailSerializer::class,
)

package com.ankushg.cocktailapp.shared.remote.models

import com.ankushg.cocktailapp.shared.local.Cocktail
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
@Serializer(forClass = Cocktail::class)
object CocktailSerializer

@Serializable
data class
CocktailResponse(val drinks: List<Cocktail>)
