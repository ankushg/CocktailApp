package com.ankushg.cocktailapp.shared.data.enums

/**
 * Note: this was manually compiled from the API as a design decision to simplify representation
 *
 * This might break in the future.
 *
 * TODO(https://github.com/ankushg/CocktailApp/issues/2): add logging to alert when this needs to be updated
 */
enum class AlcoholStatus(val strAlcoholic: String) {
    ALCOHOLIC("Alcoholic"),
    NON_ALCOHOLIC("Non alcoholic"),
    ALCOHOL_OPTIONAL("Optional alcohol");

    companion object {
        fun forValue(strAlcoholic: String) = values()
            .firstOrNull { it.strAlcoholic == strAlcoholic }
            ?: ALCOHOL_OPTIONAL
    }
}
