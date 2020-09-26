@file:UseSerializers(
    DrinkCategoryAsStringSerializer::class,
    GlassAsStringSerializer::class,
    AlcoholStatusAsStringSerializer::class
)

package com.ankushg.cocktailapp.shared.remote.models

import com.ankushg.cocktailapp.shared.domain.entities.DomainCocktail
import com.ankushg.cocktailapp.shared.domain.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.domain.enums.Glass
import com.ankushg.cocktailapp.shared.remote.mappers.AlcoholStatusAsStringSerializer
import com.ankushg.cocktailapp.shared.remote.mappers.DrinkCategoryAsStringSerializer
import com.ankushg.cocktailapp.shared.remote.mappers.GlassAsStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class RemoteCocktailSummary(
    val idDrink: Long,
    val strDrink: String,
    val strDrinkThumb: String
)

@Serializable
data class CocktailSummaryResponse(val drinks: List<RemoteCocktailSummary>)

@Serializable
data class RemoteCocktail(
    val idDrink: Long,
    val strDrink: String,
    val strDrinkThumb: String,
    val strDrinkAlternate: String? = null,
    val strDrinkES: String? = null,
    val strDrinkDE: String? = null,
    val strDrinkFR: String? = null,
    @SerialName("strDrinkZH-HANS")
    val strDrinkZH_HANS: String? = null,
    @SerialName("strDrinkZH-HANT")
    val strDrinkZH_HANT: String? = null,
    val strTags: String? = null,
    val strVideo: String? = null,
    val strCategory: DrinkCategory? = null,
    val strIBA: String? = null,
    val strAlcoholic: AlcoholStatus? = null,
    val strGlass: Glass? = null,
    val strInstructions: String? = null,
    val strInstructionsES: String? = null,
    val strInstructionsDE: String? = null,
    val strInstructionsFR: String? = null,
    @SerialName("strInstructionsZH-HANS")
    val strInstructionsZH_HANS: String? = null,
    @SerialName("strInstructionsZH-HANT")
    val strInstructionsZH_HANT: String? = null,
    val strIngredient1: String? = null,
    val strIngredient2: String? = null,
    val strIngredient3: String? = null,
    val strIngredient4: String? = null,
    val strIngredient5: String? = null,
    val strIngredient6: String? = null,
    val strIngredient7: String? = null,
    val strIngredient8: String? = null,
    val strIngredient9: String? = null,
    val strIngredient10: String? = null,
    val strIngredient11: String? = null,
    val strIngredient12: String? = null,
    val strIngredient13: String? = null,
    val strIngredient14: String? = null,
    val strIngredient15: String? = null,
    val strMeasure1: String? = null,
    val strMeasure2: String? = null,
    val strMeasure3: String? = null,
    val strMeasure4: String? = null,
    val strMeasure5: String? = null,
    val strMeasure6: String? = null,
    val strMeasure7: String? = null,
    val strMeasure8: String? = null,
    val strMeasure9: String? = null,
    val strMeasure10: String? = null,
    val strMeasure11: String? = null,
    val strMeasure12: String? = null,
    val strMeasure13: String? = null,
    val strMeasure14: String? = null,
    val strMeasure15: String? = null,
    val strCreativeCommonsConfirmed: String? = null,
    val dateModified: String? = null
)

fun RemoteCocktail.toDomainCocktail(): DomainCocktail {
    return DomainCocktail(
        idDrink = idDrink,
        strDrink = strDrink,
        strDrinkThumb = strDrinkThumb,
        strDrinkAlternate = strDrinkAlternate,
        strDrinkES = strDrinkES,
        strDrinkDE = strDrinkDE,
        strDrinkFR = strDrinkFR,
        strDrinkZH_HANS = strDrinkZH_HANS,
        strDrinkZH_HANT = strDrinkZH_HANT,
        strTags = strTags,
        strVideo = strVideo,
        strCategory = strCategory,
        strIBA = strIBA,
        strAlcoholic = strAlcoholic,
        strGlass = strGlass,
        strInstructions = strInstructions,
        strInstructionsES = strInstructionsES,
        strInstructionsDE = strInstructionsDE,
        strInstructionsFR = strInstructionsFR,
        strInstructionsZH_HANS = strInstructionsZH_HANS,
        strInstructionsZH_HANT = strInstructionsZH_HANT,
        strIngredient1 = strIngredient1,
        strIngredient2 = strIngredient2,
        strIngredient3 = strIngredient3,
        strIngredient4 = strIngredient4,
        strIngredient5 = strIngredient5,
        strIngredient6 = strIngredient6,
        strIngredient7 = strIngredient7,
        strIngredient8 = strIngredient8,
        strIngredient9 = strIngredient9,
        strIngredient10 = strIngredient10,
        strIngredient11 = strIngredient11,
        strIngredient12 = strIngredient12,
        strIngredient13 = strIngredient13,
        strIngredient14 = strIngredient14,
        strIngredient15 = strIngredient15,
        strMeasure1 = strMeasure1,
        strMeasure2 = strMeasure2,
        strMeasure3 = strMeasure3,
        strMeasure4 = strMeasure4,
        strMeasure5 = strMeasure5,
        strMeasure6 = strMeasure6,
        strMeasure7 = strMeasure7,
        strMeasure8 = strMeasure8,
        strMeasure9 = strMeasure9,
        strMeasure10 = strMeasure10,
        strMeasure11 = strMeasure11,
        strMeasure12 = strMeasure12,
        strMeasure13 = strMeasure13,
        strMeasure14 = strMeasure14,
        strMeasure15 = strMeasure15,
        strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
        dateModified = dateModified
    )
}

@Serializable
data class CocktailResponse(val drinks: List<RemoteCocktail>)

