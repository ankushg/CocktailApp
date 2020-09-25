package com.ankushg.cocktailapp.shared.app

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.data.placeholders.allDrinkCategories
import com.ankushg.cocktailapp.shared.data.placeholders.cocktailSummaries
import com.ankushg.cocktailapp.shared.data.placeholders.margarita
import com.ankushg.cocktailapp.shared.data.placeholders.vodka
import com.ankushg.cocktailapp.shared.domain.entities.recipeIngredients
import com.ankushg.cocktailapp.shared.injectWith
import com.ankushg.cocktailapp.shared.utils.CStateFlow
import com.ankushg.cocktailapp.shared.utils.Navigator
import com.ankushg.cocktailapp.shared.utils.wrap
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class CommonAppViewModel : AppViewModel, KoinComponent {
    private val log: Kermit by injectWith("CommonAppViewModel")

    companion object {
        // TODO: use a better initial state
        private val initialState = ViewState.CategoryList(allDrinkCategories)
    }

    private val navigator = Navigator<ViewState>()

    private val mutableStateFlow: MutableStateFlow<ViewState?> = MutableStateFlow(null)
    override val stateFlow: CStateFlow<ViewState?>
        get() = mutableStateFlow.wrap(MainScope()) // TODO: don't necessarily use main scope

    init {
        ensureNeverFrozen()
        emitState(initialState)
    }

    override fun submitAction(action: Action) {
        log.d { "Received action: $action" }

        val newState: ViewState? = when (action) {
            is Action.SelectCategory -> processCategorySelection(action)
            is Action.SelectDrink -> processDrinkSelection(action)
            is Action.SelectIngredient -> processIngredientSelection(action)
            Action.UpPress -> processUpPress()
            Action.BackPress -> processBackPress()
        }

        emitState(newState)
    }

    private fun emitState(newState: ViewState?) {
        if (newState != null && newState != mutableStateFlow.value) {
            mutableStateFlow.value = newState
            navigator.navigate(newState)

            check(navigator.current == stateFlow.value) {
                "Navigator and stateFlow have mismatching states!"
            }
        }
    }

    private fun processCategorySelection(selectCategory: Action.SelectCategory): ViewState? {
        return ViewState.CocktailList(cocktailSummaries)
    }

    private fun processDrinkSelection(selectDrink: Action.SelectDrink): ViewState? {
        return ViewState.DrinkDetails(margarita, margarita.recipeIngredients)
    }

    private fun processIngredientSelection(selectIngredient: Action.SelectIngredient): ViewState? {
        return ViewState.IngredientDetails(vodka, cocktailSummaries)
    }

    private fun processUpPress(): ViewState? {
        return navigator.back()
    }

    private fun processBackPress(): ViewState? {
        // TODO: allow users to back all the way out of the app!
        return navigator.back()
    }
}