package com.ankushg.cocktailapp.shared.data.repositories

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.data.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.data.enums.Glass
import com.ankushg.cocktailapp.shared.local.Cocktail
import com.ankushg.cocktailapp.shared.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.remote.CocktailApi
import com.ankushg.cocktailapp.shared.remote.models.CocktailResponse
import kotlinx.coroutines.flow.Flow

class CocktailRepository(
    private val dbHelper: DatabaseHelper,
    private val cocktailApi: CocktailApi,
    private val log: Kermit
) {
    init {
        ensureNeverFrozen()
    }

    fun selectAllCocktails(): Flow<List<Cocktail>> =
        dbHelper.selectAllCocktails()

    fun selectCocktailsByMultipleParameters(
        nameSubstring: String? = null,
        id: Long? = null,
        alcoholic: AlcoholStatus? = null,
        glass: Glass? = null,
        category: DrinkCategory? = null
    ): Flow<List<Cocktail>> =
        dbHelper.selectCocktailsByMultipleParameters(
            nameSubstring = nameSubstring,
            id = id,
            alcoholic = alcoholic,
            glass = glass,
            category = category
        )

    fun selectCocktailByName(name: String): Flow<Cocktail> =
        dbHelper.selectCocktailsByName(name)

    fun selectCocktailById(id: Long): Flow<Cocktail> =
        dbHelper.selectCocktailById(id)

    suspend fun updateCocktailsByCategory(category: DrinkCategory) =
        cocktailApi.cocktailsByCategory(category)
            .importToLocal()

    suspend fun updateCocktailsByAlcoholStatus(alcoholic: AlcoholStatus) =
        cocktailApi.cocktailsByAlcoholicStatus(alcoholic)
            .importToLocal()

    suspend fun updateCocktailsByGlass(glass: Glass) =
        cocktailApi.cocktailsByGlass(glass)
            .importToLocal()

    suspend fun updateCocktailDetailsById(id: Long) =
        cocktailApi.fetchFullCocktailDetails(id)
            .importToLocal()

    suspend fun updateCocktailDetailsByName(name: String) =
        cocktailApi.cocktailsByName(name)
            .importToLocal()

    private suspend fun CocktailResponse.importToLocal() {
        log.d { "Storing cocktail network response to DB: $this" }
        dbHelper.insertCocktails(this.drinks)
    }
}
