package com.ankushg.cocktailapp.android.ui

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.ankushg.cocktailapp.android.ui.utils.Navigator
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import kotlinx.android.parcel.Parcelize

/**
 * Models the screens in the app and any arguments they require.
 */
sealed class Destination : Parcelable {
    @Parcelize
    object CategoryList : Destination()

    @Parcelize
    @Immutable
    data class DrinksByCategory(val category: DrinkCategory) : Destination()

    @Immutable
    @Parcelize
    data class DrinkDetails(val idDrink: Long) : Destination()

    @Immutable
    @Parcelize
    data class IngredientDetails(val strIngredient: String) : Destination()
}

/**
 * Models the navigation actions in the app.
 */
class Actions(navigator: Navigator<Destination>) {
    val selectCategory: (DrinkCategory) -> Unit = { category ->
        navigator.navigate(Destination.DrinksByCategory(category))
    }

    val selectDrink: (Long) -> Unit = { idDrink ->
        navigator.navigate(Destination.DrinkDetails(idDrink))
    }

    val selectIngredient: (String) -> Unit = { strIngredient ->
        navigator.navigate(Destination.IngredientDetails(strIngredient))
    }

    val upPress: () -> Unit = {
        navigator.back()
    }
}