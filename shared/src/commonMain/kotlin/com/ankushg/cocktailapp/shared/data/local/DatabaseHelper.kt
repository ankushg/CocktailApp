package com.ankushg.cocktailapp.shared.data.local

import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.CocktailsDb
import com.ankushg.cocktailapp.shared.data.local.mappers.cocktailAdapter
import com.ankushg.cocktailapp.shared.data.remote.models.IngredientSummary
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

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
            dbRef.breedQueries.updateFavorite(favorite.toLong(), breedId)
        }
    }
    // endregion

    // region Ingredients
    fun selectAllIngredients() = dbRef.ingredientQueries
        .selectAll()
        .asFlow()
        .mapToList()
        .flowOn(backgroundDispatcher)

    fun selectIngredient(ingredientName: String) = dbRef.ingredientQueries
        .selectByName(ingredientName)
        .asFlow()
        .mapToOne()
        .flowOn(backgroundDispatcher)

    suspend fun insertIngredientSummaries(ingredientSummaries: List<IngredientSummary>) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            ingredientSummaries
                .map { it.strIngredient1 }
                .forEach(dbRef.ingredientQueries::insertSummary)
        }
    }

    suspend fun insertIngredientDetails(ingredients: Collection<Ingredient>) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            ingredients
                .forEach(dbRef.ingredientQueries::insertFull)
        }
    }
    //endregion
}

fun Breed.isFavorited(): Boolean = this.favorite != 0L
internal fun Boolean.toLong(): Long = if (this) 1L else 0L
