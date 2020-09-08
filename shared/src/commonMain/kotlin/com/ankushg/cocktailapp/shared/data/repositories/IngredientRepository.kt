package com.ankushg.cocktailapp.shared.data.repositories

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.data.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.data.local.Ingredient
import com.ankushg.cocktailapp.shared.data.remote.CocktailApi
import com.ankushg.cocktailapp.shared.data.remote.models.IngredientDetailResponse
import com.ankushg.cocktailapp.shared.data.remote.models.IngredientNameResponse
import kotlinx.coroutines.flow.Flow
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
