package com.ankushg.cocktailapp.shared.data.repositories

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.domain.entities.DomainCocktail
import com.ankushg.cocktailapp.shared.domain.entities.DomainCocktailSummary
import com.ankushg.cocktailapp.shared.domain.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.domain.enums.Glass
import com.ankushg.cocktailapp.shared.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.remote.CocktailApi
import com.ankushg.cocktailapp.shared.remote.models.CocktailResponse
import com.ankushg.cocktailapp.shared.remote.models.CocktailSummaryResponse
import com.ankushg.cocktailapp.shared.remote.models.toDomainCocktail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CocktailRepository(
    private val dbHelper: DatabaseHelper,
    private val cocktailApi: CocktailApi,
    private val log: Kermit
) {
    init {
        ensureNeverFrozen()
    }

    fun selectAllCocktails(): Flow<List<DomainCocktail>> =
        dbHelper.selectAllCocktails()

    fun selectCocktailsByMultipleParameters(
        nameSubstring: String? = null,
        id: Long? = null,
        alcoholic: AlcoholStatus? = null,
        glass: Glass? = null,
        category: DrinkCategory? = null
    ): Flow<List<DomainCocktail>> =
        dbHelper.selectCocktailsByMultipleParameters(
            nameSubstring = nameSubstring,
            id = id,
            alcoholic = alcoholic,
            glass = glass,
            category = category
        )

    fun selectCocktailByName(name: String): Flow<DomainCocktail> =
        dbHelper.selectCocktailsByName(name)

    fun selectCocktailById(id: Long): Flow<DomainCocktail> =
        dbHelper.selectCocktailById(id)

    suspend fun updateCocktailsByCategory(category: DrinkCategory) =
        cocktailApi.cocktailsByCategory(category)
            .importToLocalWithCategory(category)

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
        dbHelper.insertCocktails(this.drinks.map { it.toDomainCocktail() })
    }
    }
}
