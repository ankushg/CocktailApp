package com.ankushg.cocktailapp.shared.domain.enums

/**
 * Note: this was manually compiled from the API as a design decision to simplify representation
 *
 * This might break in the future.
 *
 * TODO(https://github.com/ankushg/CocktailApp/issues/2): add logging to alert when this needs to be updated
 */
enum class Glass(val strGlass: String) {
    HIGHBALL_GLASS("Highball glass"),
    COCKTAIL_GLASS("Cocktail glass"),
    OLD_FASHIONED_GLASS("Old-fashioned glass"),
    COLLING_GLASS("Collins glass"),
    POUSSE_CAFE_GLASS("Pousse cafe glass"),
    CHAMPAGNE_GLASS("Champagne flute"),
    WHISKEY_SOUR_GLASS("Whiskey sour glass"),
    CORDIAL_GLASS("Cordial glass"),
    BRANDY_SNIFTER("Brandy snifter"),
    WHITE_WINE_GLASS("White wine glass"),
    NICK_AND_NORA_GLASS("Nick and Nora Glass"),
    HURRICANE_GLASS("Hurricane glass"),
    COFFEE_MUG("Coffee mug"),
    SHOT_GLASS("Shot glass"),
    JAR("Jar"),
    IRISH_COFFEE_CUP("Irish coffee cup"),
    PUNCH_BOAL("Punch bowl"),
    PITCHER("Pitcher"),
    PINT_GLASS("Pint glass"),
    COPPER_MUG("Copper Mug"),
    WINE_GLASS("Wine Glass"),
    BEER_MUG("Beer mug"),
    MARGARITA_COUPETTE_GLASS("Margarita/Coupette glass"),
    BEER_PILSNER("Beer pilsner"),
    BEER_GLASS("Beer Glass"),
    PARFAIT_GLASS("Parfait glass"),
    MASON_JAR("Mason jar"),
    MARGARITA_GLASS("Margarita glass"),
    MARTINI_GLASS("Martini Glass"),
    BALLOON_GLASS("Balloon Glass"),
    COUPE_GLASS("Coupe Glass"),
    OTHER("");

    companion object {
        fun forValue(strGlass: String) = Glass.values()
            .firstOrNull { it.strGlass == strGlass }
            ?: Glass.OTHER
    }
}

