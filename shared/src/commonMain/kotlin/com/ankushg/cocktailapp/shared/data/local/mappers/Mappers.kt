package com.ankushg.cocktailapp.shared.data.local.mappers

import com.ankushg.cocktailapp.shared.data.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.data.enums.Glass
import com.ankushg.cocktailapp.shared.data.local.Cocktail
import com.squareup.sqldelight.ColumnAdapter

internal val drinkCategoryAdapter = object : ColumnAdapter<DrinkCategory, String> {
    override fun decode(databaseValue: String): DrinkCategory =
        DrinkCategory.forValue(databaseValue)

    override fun encode(value: DrinkCategory): String = value.strCategory
}

internal val alcoholStatusAdapter = object : ColumnAdapter<AlcoholStatus, String> {
    override fun decode(databaseValue: String): AlcoholStatus =
        AlcoholStatus.forValue(databaseValue)

    override fun encode(value: AlcoholStatus) = value.strAlcoholic
}

internal val glassAdapter = object : ColumnAdapter<Glass, String> {
    override fun decode(databaseValue: String): Glass = Glass.forValue(databaseValue)
    override fun encode(value: Glass): String = value.strGlass
}

val cocktailAdapter = Cocktail.Adapter(
    strCategoryAdapter = drinkCategoryAdapter,
    strAlcoholicAdapter = alcoholStatusAdapter,
    strGlassAdapter = glassAdapter
)
