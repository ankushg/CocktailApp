package com.ankushg.cocktailapp.shared.data.remote.models

import com.ankushg.cocktailapp.shared.data.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.data.enums.Glass
import com.ankushg.cocktailapp.shared.data.local.Cocktail
import com.ankushg.cocktailapp.shared.data.local.Ingredient
import com.ankushg.cocktailapp.shared.data.local.SelectSummaryById

data class CocktailSummaryResponse(val drinks: List<SelectSummaryById>)
data class CocktailDetailResponse(val drinks: List<Cocktail>)
data class IngredientResponse(val ingredients: List<Ingredient>)
data class CategoryResponse(val drinks: List<DrinkCategory>)
data class GlassResponse(val drinks: List<Glass>)
data class AlcoholStatusResponse(val drinks: List<AlcoholStatus>)

