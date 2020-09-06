package com.ankushg.cocktailapp.shared

import com.ankushg.cocktailapp.shared.data.repositories.BreedModel
import com.ankushg.cocktailapp.shared.data.repositories.ItemDataSummary
import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.data.local.Breed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class NativeViewModel(
    private val viewUpdate: (ItemDataSummary) -> Unit,
    private val errorUpdate: (String) -> Unit
) : KoinComponent {

    private val log: Kermit by inject { parametersOf("BreedModel") }
    private val scope = MainScope(Dispatchers.Main, log)
    private val breedModel: BreedModel

    init {
        ensureNeverFrozen()
        breedModel = BreedModel()
        observeBreeds()
    }

    private fun observeBreeds() {
        scope.launch {
            log.v { "Observe Breeds" }
            breedModel.selectAllBreeds()
                .collect { summary ->
                    log.v { "Collecting Things" }
                    viewUpdate(summary)
                }
        }
    }

    fun getBreedsFromNetwork() {
        scope.launch {
            breedModel.getBreedsFromNetwork()?.let { errorString ->
                errorUpdate(errorString)
            }
        }
    }

    fun updateBreedFavorite(breed: Breed) {
        scope.launch {
            breedModel.updateBreedFavorite(breed)
        }
    }

    fun onDestroy() {
        scope.onDestroy()
    }
}
