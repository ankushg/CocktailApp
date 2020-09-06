package com.ankushg.cocktailapp.shared.data.repositories

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.currentTimeMillis
import com.ankushg.cocktailapp.shared.data.entities.BreedDataSummary
import com.ankushg.cocktailapp.shared.data.local.Breed
import com.ankushg.cocktailapp.shared.data.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.data.remote.DogApi
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class BreedRepository() : KoinComponent {
    private val dbHelper: DatabaseHelper by inject()
    private val settings: Settings by inject()
    private val dogApi: DogApi by inject()
    private val log: Kermit by inject { parametersOf("BreedModel") }

    companion object {
        internal const val DB_TIMESTAMP_KEY = "DbTimestampKey"
    }

    init {
        ensureNeverFrozen()
    }

    fun selectAllBreeds() =
        dbHelper.selectAllItems()
            .map { itemList ->
                log.v { "Select all query dirtied" }
                BreedDataSummary(
                    itemList.maxByOrNull { it?.name?.length ?: 0 },
                    itemList
                )
            }

    suspend fun getBreedsFromNetwork(): String? {
        fun isBreedListStale(currentTimeMS: Long): Boolean {
            val lastDownloadTimeMS = settings.getLong(DB_TIMESTAMP_KEY, 0)
            val oneHourMS = 60 * 60 * 1000
            return (lastDownloadTimeMS + oneHourMS < currentTimeMS)
        }

        val currentTimeMS = currentTimeMillis()
        if (isBreedListStale(currentTimeMS)) {
            try {
                val breedResult = dogApi.fetchBreeds()
                log.v { "Breed network result: ${breedResult.status}" }
                val breedList = breedResult.message.keys.toList()
                log.v { "Fetched ${breedList.size} breeds from network" }
                dbHelper.insertBreeds(breedList)
                settings.putLong(DB_TIMESTAMP_KEY, currentTimeMS)
            } catch (e: Exception) {
                return "Unable to download breed list"
            }
        } else {
            log.i { "Breeds not fetched from network. Recently updated" }
        }
        return null
    }

    suspend fun updateBreedFavorite(breed: Breed) {
        dbHelper.updateFavorite(breed.id, breed.favorite != 1L)
    }
}

