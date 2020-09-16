package com.ankushg.cocktailapp.android.placeholders.cocktails

import com.ankushg.cocktailapp.shared.data.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.data.enums.Glass
import com.ankushg.cocktailapp.shared.local.Cocktail

val margaritaSummary = buildCocktail(
    idDrink = 11007,
    strDrink = "Margarita",
    strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
)

val margarita = buildCocktail(
    idDrink = 11007,
    strDrink = "Margarita",
    strDrinkAlternate = null,
    strDrinkES = null,
    strDrinkDE = null,
    strDrinkFR = null,
    strDrinkZH_HANS = null,
    strDrinkZH_HANT = null,
    strTags = "IBA,ContemporaryClassic",
    strVideo = null,
    strCategory = DrinkCategory.ORDINARY_DRINK,
    strIBA = "Contemporary Classics",
    strAlcoholic = AlcoholStatus.ALCOHOLIC,
    strGlass = Glass.COCKTAIL_GLASS,
    strInstructions = "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass.",
    strInstructionsES = null,
    strInstructionsDE = "Reiben Sie den Rand des Glases mit der Limettenscheibe, damit das Salz daran haftet. Achten Sie darauf, dass nur der \u00e4u\u00dfere Rand angefeuchtet wird und streuen Sie das Salz darauf. Das Salz sollte sich auf den Lippen des Genie\u00dfers befinden und niemals in den Cocktail einmischen. Die anderen Zutaten mit Eis sch\u00fctteln und vorsichtig in das Glas geben.",
    strInstructionsFR = null,
    strInstructionsZH_HANS = null,
    strInstructionsZH_HANT = null,
    strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
    strIngredient1 = "Tequila",
    strIngredient2 = "Triple sec",
    strIngredient3 = "Lime juice",
    strIngredient4 = "Salt",
    strIngredient5 = null,
    strIngredient6 = null,
    strIngredient7 = null,
    strIngredient8 = null,
    strIngredient9 = null,
    strIngredient10 = null,
    strIngredient11 = null,
    strIngredient12 = null,
    strIngredient13 = null,
    strIngredient14 = null,
    strIngredient15 = null,
    strMeasure1 = "1 1/2 oz ",
    strMeasure2 = "1/2 oz ",
    strMeasure3 = "1 oz ",
    strMeasure4 = null,
    strMeasure5 = null,
    strMeasure6 = null,
    strMeasure7 = null,
    strMeasure8 = null,
    strMeasure9 = null,
    strMeasure10 = null,
    strMeasure11 = null,
    strMeasure12 = null,
    strMeasure13 = null,
    strMeasure14 = null,
    strMeasure15 = null,
    strCreativeCommonsConfirmed = "Yes",
    dateModified = "2015-08-18 14:42:59"
)

/**
 * Convenience function because [Cocktail] doesn't have defaults for nullable params
 */
internal fun buildCocktail(
    idDrink: Long,
    strDrink: String,
    strDrinkThumb: String,
    strDrinkAlternate: String? = null,
    strDrinkES: String? = null,
    strDrinkDE: String? = null,
    strDrinkFR: String? = null,
    strDrinkZH_HANS: String? = null,
    strDrinkZH_HANT: String? = null,
    strTags: String? = null,
    strVideo: String? = null,
    strCategory: DrinkCategory? = null,
    strIBA: String? = null,
    strAlcoholic: AlcoholStatus? = null,
    strGlass: Glass? = null,
    strInstructions: String? = null,
    strInstructionsES: String? = null,
    strInstructionsDE: String? = null,
    strInstructionsFR: String? = null,
    strInstructionsZH_HANS: String? = null,
    strInstructionsZH_HANT: String? = null,
    strIngredient1: String? = null,
    strIngredient2: String? = null,
    strIngredient3: String? = null,
    strIngredient4: String? = null,
    strIngredient5: String? = null,
    strIngredient6: String? = null,
    strIngredient7: String? = null,
    strIngredient8: String? = null,
    strIngredient9: String? = null,
    strIngredient10: String? = null,
    strIngredient11: String? = null,
    strIngredient12: String? = null,
    strIngredient13: String? = null,
    strIngredient14: String? = null,
    strIngredient15: String? = null,
    strMeasure1: String? = null,
    strMeasure2: String? = null,
    strMeasure3: String? = null,
    strMeasure4: String? = null,
    strMeasure5: String? = null,
    strMeasure6: String? = null,
    strMeasure7: String? = null,
    strMeasure8: String? = null,
    strMeasure9: String? = null,
    strMeasure10: String? = null,
    strMeasure11: String? = null,
    strMeasure12: String? = null,
    strMeasure13: String? = null,
    strMeasure14: String? = null,
    strMeasure15: String? = null,
    strCreativeCommonsConfirmed: String? = null,
    dateModified: String? = null
): Cocktail {
    return Cocktail(
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
        dateModified = dateModified,
    )
}

val cocktailSummaries = List(10) { margaritaSummary }

val Cocktail.strSmallUrl
    get() = "$strDrinkThumb/preview"

data class RecipeIngredient(
    val strThumbnailUrl: String,
    val strIngredient: String,
    val strMeasure: String? = null
)

val Cocktail.recipeIngredients: List<RecipeIngredient>
    get() = listOfNotNull(
        strIngredient1?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure1)
        },
        strIngredient2?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure2)
        },
        strIngredient3?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure3)
        },
        strIngredient4?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure4)
        },
        strIngredient5?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure5)
        },
        strIngredient6?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure6)
        },
        strIngredient7?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure7)
        },
        strIngredient8?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure8)
        },
        strIngredient9?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure9)
        },
        strIngredient10?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure10)
        },
        strIngredient11?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure11)
        },
        strIngredient12?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure12)
        },
        strIngredient13?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure13)
        },
        strIngredient14?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure14)
        },
        strIngredient15?.let {
            RecipeIngredient(it, it.asSmallIngredientImageUrl(), strMeasure15)
        }
    )