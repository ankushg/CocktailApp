package com.ankushg.cocktailapp.shared.local

import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.CocktailsDb
import com.ankushg.cocktailapp.shared.domain.entities.DomainCocktailSummary
import com.ankushg.cocktailapp.shared.domain.entities.DomainIngredient
import com.ankushg.cocktailapp.shared.domain.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.domain.enums.Glass
import com.ankushg.cocktailapp.shared.local.mappers.cocktailAdapter
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

class DatabaseHelper(
    sqlDriver: SqlDriver,
    private val log: Kermit,
    private val backgroundDispatcher: CoroutineDispatcher
) {
    private val dbRef: CocktailsDb = CocktailsDb(sqlDriver, cocktailAdapter)

    // region Ingredients
    fun selectAllIngredients(): Flow<List<Ingredient>> {
        log.d { "Querying DB for all ingredients" }
        return dbRef.ingredientQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)
            .onEach {
                log.d { "Emitting ${it.size}  updated ingredients from db" }
            }
    }

    fun selectIngredient(ingredientName: String): Flow<Ingredient> {
        log.d { "Querying DB for ingredient: $ingredientName" }
        return dbRef.ingredientQueries
            .selectByName(ingredientName)
            .asFlow()
            .mapToOne()
            .flowOn(backgroundDispatcher)
            .onEach {
                log.d { "Emitting updated ingredient $ingredientName from db: $it" }
            }
    }

    suspend fun insertIngredientSummaries(ingredientSummaries: List<DomainIngredient>) {
        log.d { "Inserting ${ingredientSummaries.size} ingredient summaries into database" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            ingredientSummaries
                .map { it.strIngredient }
                .forEach(dbRef.ingredientQueries::insertSummary)
        }
    }

    suspend fun insertIngredientDetails(ingredients: Collection<Ingredient>) {
        log.d { "Inserting ${ingredients.size} detailed ingredients into database" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            ingredients
                .forEach(dbRef.ingredientQueries::insertFull)
        }
    }
    //endregion

    // region Cocktails
    fun selectAllCocktails(): Flow<List<Cocktail>> {
        log.d { "Querying DB for all ingredients" }

        return dbRef.cocktailQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)
            .onEach {
                log.d { "Emitting ${it.size}  updated cocktails from db" }
            }
    }

    fun selectCocktailById(id: Long): Flow<Cocktail> {
        log.d { "Querying DB for cocktail: $id" }

        return dbRef.cocktailQueries
            .selectById(id)
            .asFlow()
            .mapToOne()
            .flowOn(backgroundDispatcher)
            .onEach {
                log.d { "Emitting updated cocktail $id from db: $it" }
            }
    }

    fun selectCocktailsByName(name: String): Flow<Cocktail> {
        log.d { "Querying DB for cocktail: $name" }

        return dbRef.cocktailQueries
            .selectByName(name)
            .asFlow()
            .mapToOne()
            .flowOn(backgroundDispatcher)
            .onEach {
                log.d { "Emitting updated cocktail $name from db: $it" }
            }
    }

    fun selectCocktailsByMultipleParameters(
        nameSubstring: String? = null,
        id: Long? = null,
        alcoholic: AlcoholStatus? = null,
        glass: Glass? = null,
        category: DrinkCategory? = null
    ) = dbRef.cocktailQueries
        .selectByMulti(
            nameSubstring = nameSubstring,
            id = id,
            alcoholic = alcoholic,
            glass = glass,
            category = category
        )
        .asFlow()
        .mapToList()
        .flowOn(backgroundDispatcher)

    suspend fun insertCocktails(cocktails: Collection<Cocktail>) {
        log.d { "Inserting ${cocktails.size} cocktails into database" }

        dbRef.transactionWithContext(backgroundDispatcher) {
            cocktails
                .forEach(dbRef.cocktailQueries::insertFullCocktail)
        }
    }
    // endregion
}
