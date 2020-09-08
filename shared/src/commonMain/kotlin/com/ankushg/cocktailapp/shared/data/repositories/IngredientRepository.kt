package com.ankushg.cocktailapp.shared.data.repositories

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.data.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.data.remote.CocktailApi
import com.ankushg.cocktailapp.shared.data.remote.models.IngredientDetailResponse
import com.ankushg.cocktailapp.shared.data.remote.models.IngredientNameResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class IngredientRepository : KoinComponent {
    private val dbHelper: DatabaseHelper by inject()
    private val cocktailApi: CocktailApi by inject()
    private val log: Kermit by inject { parametersOf("IngredientRepository") }

    init {
        ensureNeverFrozen()
    }

    suspend fun updateIngredientList() = cocktailApi.indexIngredients()
        .importToLocal()

    fun listIngredients() = dbHelper.selectAllIngredients()

    suspend fun updateIngredient(name: String) = cocktailApi.fetchIngredientByName(name)
        .importToLocal()

    fun queryIngredient(ingredientName: String) = dbHelper.selectIngredient(ingredientName)

    private suspend fun IngredientNameResponse.importToLocal() =
        dbHelper.insertIngredientSummaries(this.ingredients)

    private suspend fun IngredientDetailResponse.importToLocal() =
        dbHelper.insertIngredientDetails(this.ingredients)
}
