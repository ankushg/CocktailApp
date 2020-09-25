package com.ankushg.cocktailapp.shared.data.repositories

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.local.Ingredient
import com.ankushg.cocktailapp.shared.remote.CocktailApi
import com.ankushg.cocktailapp.shared.remote.models.IngredientDetailResponse
import com.ankushg.cocktailapp.shared.remote.models.IngredientNameResponse
import kotlinx.coroutines.flow.Flow

class IngredientRepository(
    private val dbHelper: DatabaseHelper,
    private val cocktailApi: CocktailApi,
    private val log: Kermit
) {
    init {
        ensureNeverFrozen()
    }

    fun listIngredients(): Flow<List<Ingredient>> {
        return dbHelper.selectAllIngredients()
    }

    fun queryIngredient(ingredientName: String): Flow<Ingredient> =
        dbHelper.selectIngredient(ingredientName)

    suspend fun updateIngredientList() =
        cocktailApi.indexIngredients()
            .importToLocal()

    suspend fun updateIngredient(name: String) =
        cocktailApi.fetchIngredientByName(name)
            .importToLocal()

    private suspend fun IngredientNameResponse.importToLocal() {
        log.d { "Storing ingredient name response to DB: $this" }
        dbHelper.insertIngredientSummaries(this.ingredients)
    }

    private suspend fun IngredientDetailResponse.importToLocal() {
        log.d { "Storing ingredient detail response to DB: $this" }
        dbHelper.insertIngredientDetails(this.ingredients)
    }
}
