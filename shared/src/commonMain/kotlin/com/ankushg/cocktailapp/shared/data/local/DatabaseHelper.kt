package com.ankushg.cocktailapp.shared.data.local

import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.CocktailsDb
import com.ankushg.cocktailapp.shared.data.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.data.enums.Glass
import com.ankushg.cocktailapp.shared.data.local.mappers.cocktailAdapter
import com.ankushg.cocktailapp.shared.data.remote.models.IngredientSummary
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

    // region Breeds
    fun selectAllBreeds(): Flow<List<Breed>> =
        dbRef.breedQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)

    suspend fun insertBreeds(breedNames: List<String>) {
        log.d { "Inserting ${breedNames.size} breeds into database" }

        dbRef.transactionWithContext(backgroundDispatcher) {
            breedNames.forEach { name ->
                dbRef.breedQueries.insertBreed(null, name, 0)
            }
        }
    }

    fun selectBreedsById(id: Long): Flow<List<Breed>> =
        dbRef.breedQueries
            .selectById(id)
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)

    suspend fun deleteAllBreeds() {
        log.i { "Database Cleared" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.breedQueries.deleteAll()
        }
    }

    suspend fun updateBreedFavoriteStatus(breedId: Long, favorite: Boolean) {
        log.i { "Breed $breedId: Favorited $favorite" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.breedQueries.updateFavorite(if (favorite) 1L else 0L, breedId)
        }
    }
    // endregion

    // region Ingredients
    fun selectAllIngredients(): Flow<List<Ingredient>> {
        log.d { "Querying DB for all ingredients" }
        return dbRef.ingredientQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)
            .onEach {
                log.d { "Emitting updated ingredients from db: $it" }
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

    suspend fun insertIngredientSummaries(ingredientSummaries: List<IngredientSummary>) {
        log.d { "Inserting ${ingredientSummaries.size} ingredient summaries into database" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            ingredientSummaries
                .map { it.strIngredient1 }
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
                log.d { "Emitting updated cocktails from db: $it" }
            }
    }

    fun selectCocktailByName(name: String): Flow<Cocktail> {
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
