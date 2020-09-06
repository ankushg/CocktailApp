package com.ankushg.cocktailapp.shared.data.local
import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.CocktailsDb
import com.ankushg.cocktailapp.shared.data.local.mappers.cocktailAdapter
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

    fun selectAllItems(): Flow<List<Breed>> =
        dbRef.tableQueries
            .selectAll()
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)

    suspend fun insertBreeds(breedNames: List<String>) {
        log.d { "Inserting ${breedNames.size} breeds into database" }

        dbRef.transactionWithContext(backgroundDispatcher) {
            breedNames.forEach { name ->
                dbRef.tableQueries.insertBreed(null, name, 0)
            }
        }
    }

    suspend fun selectById(id: Long): Flow<List<Breed>> =
        dbRef.tableQueries
            .selectById(id)
            .asFlow()
            .mapToList()
            .flowOn(backgroundDispatcher)

    suspend fun deleteAll() {
        log.i { "Database Cleared" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.tableQueries.deleteAll()
        }
    }

    suspend fun updateFavorite(breedId: Long, favorite: Boolean) {
        log.i { "Breed $breedId: Favorited $favorite" }
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.tableQueries.updateFavorite(favorite.toLong(), breedId)
        }
    }
}

fun Breed.isFavorited(): Boolean = this.favorite != 0L
internal fun Boolean.toLong(): Long = if (this) 1L else 0L
