package com.ankushg.cocktailapp.shared.app

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.data.placeholders.allDrinkCategories
import com.ankushg.cocktailapp.shared.data.placeholders.cocktailSummaries
import com.ankushg.cocktailapp.shared.data.placeholders.margarita
import com.ankushg.cocktailapp.shared.data.placeholders.vodka
import com.ankushg.cocktailapp.shared.domain.entities.recipeIngredients
import com.ankushg.cocktailapp.shared.utils.Navigator
import com.ankushg.cocktailapp.shared.utils.wrap
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow

class CommonAppViewModel(
    private val log: Kermit
) : AppViewModel {

    companion object {
        // TODO: use a better initial state
        private val initialState = ViewState.CategoryList(allDrinkCategories)
    }
    private val mutableStateFlow: MutableStateFlow<ViewState?> = MutableStateFlow(null)
    override val stateFlow: CStateFlow<ViewState?>
        get() = mutableStateFlow.wrap(MainScope()) // TODO: don't necessarily use main scope

    init {
        ensureNeverFrozen()
        emitState(initialState)
    }

    override fun submitAction(action: Action) {
        log.d { "Received action: $action" }

        val newState: ViewState? = null // TODO

        emitState(newState)
    }

    private fun emitState(newState: ViewState?) {
        if (newState != null && newState != mutableStateFlow.value) {
            mutableStateFlow.value = newState
        }
    }
}