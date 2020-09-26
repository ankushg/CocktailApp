package com.ankushg.cocktailapp.shared.domain.enums

/**
 * Note: this was manually compiled from the API as a design decision to simplify representation
 *
 * This might break in the future.
 *
 * TODO(https://github.com/ankushg/CocktailApp/issues/2): add logging to alert when this needs to be updated
 */
enum class DrinkCategory(val strCategory: String) {
    ORDINARY_DRINK("Ordinary Drink"),
    COCKTAIL("Cocktail"),
    MILK_FLOAT_SHAKE("Milk / Float / Shake"),
    COCOA("Cocoa"),
    SHOT("Shot"),
    COFFEE_TEA("Coffee / Tea"),
    HOMEMADE_LIQUEUR("Homemade Liqueur"),
    PUNCH_PARTY_DRINK("Punch / Party Drink"),
    BEER("Beer"),
    SOFT_DRINK_SODA("Soft Drink / Soda"),
    OTHER_UNKNOWN("Other/Unknown");

    companion object {
        fun forValue(strCategory: String) = values()
            .firstOrNull { it.strCategory == strCategory }
            ?: OTHER_UNKNOWN
    }
}
