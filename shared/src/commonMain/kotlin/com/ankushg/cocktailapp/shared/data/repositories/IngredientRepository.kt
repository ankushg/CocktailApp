package com.ankushg.cocktailapp.shared.data.repositories

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.domain.entities.DomainIngredient
import com.ankushg.cocktailapp.shared.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.remote.CocktailApi
import com.ankushg.cocktailapp.shared.remote.models.IngredientDetailResponse
import com.ankushg.cocktailapp.shared.remote.models.IngredientNameResponse
import com.ankushg.cocktailapp.shared.remote.models.toDomainIngredient
import kotlinx.coroutines.flow.Flow

class IngredientRepository(
    private val dbHelper: DatabaseHelper,
    private val cocktailApi: CocktailApi,
    private val log: Kermit
) {
    init {
        ensureNeverFrozen()
    }

    fun listIngredients(): Flow<List<DomainIngredient>> {
        return dbHelper.selectAllIngredients()
    }

    fun selectIngredientByName(ingredientName: String): Flow<DomainIngredient> =
        dbHelper.selectIngredient(ingredientName)

    suspend fun updateIngredientList() =
        cocktailApi.indexIngredients()
            .importToLocal()

    suspend fun updateIngredient(name: String) =
        cocktailApi.fetchIngredientByName(name)
            .importToLocal()

    private suspend fun IngredientNameResponse.importToLocal() {
        dbHelper.insertIngredientSummaries(this.ingredients.map { it.toDomainIngredient() })
    }

    private suspend fun IngredientDetailResponse.importToLocal() {
        dbHelper.insertIngredientDetails(this.ingredients.map { it.toDomainIngredient() })
    }
}
