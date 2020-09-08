package com.ankushg.cocktailapp.shared.data.repositories

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.data.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.data.enums.Glass
import com.ankushg.cocktailapp.shared.data.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.data.remote.CocktailApi
import com.ankushg.cocktailapp.shared.data.remote.models.CocktailResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class CocktailRepository : KoinComponent {
    private val dbHelper: DatabaseHelper by inject()
    private val cocktailApi: CocktailApi by inject()
    private val log: Kermit by inject { parametersOf("CocktailRepository") }

    init {
        ensureNeverFrozen()
    }

    fun selectCocktailsByMultipleParameters(
        nameSubstring: String? = null,
        id: Long? = null,
        alcoholic: AlcoholStatus? = null,
        glass: Glass? = null,
        category: DrinkCategory? = null
    ) = dbHelper.selectCocktailsByMultipleParameters(
        nameSubstring = nameSubstring,
        id = id,
        alcoholic = alcoholic,
        glass = glass,
        category = category
    )

    fun selectCocktail(name: String) =
        dbHelper.selectCocktailByName(name)

    suspend fun updateCocktailsByCategory(category: DrinkCategory) =
        cocktailApi.cocktailsByCategory(category)
            .importToLocal()

    suspend fun updateCocktailsByAlcoholStatus(alcoholic: AlcoholStatus) =
        cocktailApi.cocktailsByAlcoholicStatus(alcoholic)
            .importToLocal()

    suspend fun updateCocktailsByGlass(glass: Glass) =
        cocktailApi.cocktailsByGlass(glass)
            .importToLocal()

    suspend fun updateCocktailDetails(name: String) =
        cocktailApi.cocktailsByName(name)
            .importToLocal()

    private suspend fun CocktailResponse.importToLocal() =
        dbHelper.insertCocktails(this.drinks)
}
