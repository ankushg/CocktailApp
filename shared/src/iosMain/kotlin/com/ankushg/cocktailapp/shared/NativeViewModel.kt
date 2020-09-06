package com.ankushg.cocktailapp.shared

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.data.entities.BreedDataSummary
import com.ankushg.cocktailapp.shared.data.local.Breed
import com.ankushg.cocktailapp.shared.data.repositories.BreedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class NativeViewModel(
    private val viewUpdate: (BreedDataSummary) -> Unit,
    private val errorUpdate: (String) -> Unit
) : KoinComponent {

    private val log: Kermit by inject { parametersOf("BreedModel") }
    private val scope = MainScope(Dispatchers.Main, log)
    private val breedRepository: BreedRepository

    init {
        ensureNeverFrozen()
        breedRepository = BreedRepository()
        observeBreeds()
    }

    private fun observeBreeds() {
        scope.launch {
            log.v { "Observe Breeds" }
            breedRepository.selectAllBreeds()
                .collect { summary ->
                    log.v { "Collecting Things" }
                    viewUpdate(summary)
                }
        }
    }

    fun getBreedsFromNetwork() {
        scope.launch {
            breedRepository.getBreedsFromNetwork()?.let { errorString ->
                errorUpdate(errorString)
            }
        }
    }

    fun updateBreedFavorite(breed: Breed) {
        scope.launch {
            breedRepository.updateBreedFavorite(breed)
        }
    }

    fun onDestroy() {
        scope.onDestroy()
    }
}
