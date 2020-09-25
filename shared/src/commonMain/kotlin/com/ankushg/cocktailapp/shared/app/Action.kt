package com.ankushg.cocktailapp.shared.app

import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory

sealed class Action {
    data class SelectCategory(val drinkCategory: DrinkCategory) : Action()
    data class SelectDrink(val drinkId: Long) : Action()
    data class SelectIngredient(val strIngredient: String) : Action()
    object UpPress : Action()
    object BackPress : Action()
}