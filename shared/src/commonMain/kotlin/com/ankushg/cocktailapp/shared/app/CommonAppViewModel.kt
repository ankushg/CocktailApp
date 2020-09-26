package com.ankushg.cocktailapp.shared.app

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.data.placeholders.allDrinkCategories
import com.ankushg.cocktailapp.shared.domain.ObserveCocktailDetailsById
import com.ankushg.cocktailapp.shared.domain.ObserveCocktailsByCategory
import com.ankushg.cocktailapp.shared.domain.ObserveIngredientDetailsByName
import com.ankushg.cocktailapp.shared.domain.UpdateCocktailDetailsById
import com.ankushg.cocktailapp.shared.domain.UpdateCocktailsByCategory
import com.ankushg.cocktailapp.shared.domain.UpdateIngredientDetailsByName
import com.ankushg.cocktailapp.shared.injectWith
import com.ankushg.cocktailapp.shared.utils.CStateFlow
import com.ankushg.cocktailapp.shared.utils.Navigator
import com.ankushg.cocktailapp.shared.utils.wrap
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class CommonAppViewModel : AppViewModel, KoinComponent {
    private val log: Kermit by injectWith("CommonAppViewModel")

    private val updateCocktailsByCategory: UpdateCocktailsByCategory by inject()
    private val observeCocktailsByCategory: ObserveCocktailsByCategory by inject()

    private val observeCocktailDetailsById: ObserveCocktailDetailsById by inject()
    private val updateCocktailDetailsById: UpdateCocktailDetailsById by inject()

    private val observeIngredientDetailsByName: ObserveIngredientDetailsByName by inject()
    private val updateIngredientDetailsByName: UpdateIngredientDetailsByName by inject()

    companion object {
        // TODO: use a better initial state
        private val initialState = ViewState.CategoryList(allDrinkCategories)
    }

    private val navigator = Navigator<ViewState>()

    private val mutableStateFlow: MutableStateFlow<ViewState?> = MutableStateFlow(null)
    override val stateFlow: CStateFlow<ViewState?>
        get() = mutableStateFlow.wrap(MainScope()) // TODO: don't necessarily use main scope

    private var currentJob: Job? = null

    init {
        ensureNeverFrozen()

        emitState(initialState)
    }

    override fun submitAction(action: Action) {
        log.d { "Received action: $action" }

        val newStateFlow: Flow<ViewState?> = when (action) {
            is Action.SelectCategory -> processCategorySelection(action)
            is Action.SelectDrink -> processDrinkSelection(action)
            is Action.SelectIngredient -> processIngredientSelection(action)
            Action.UpPress -> processUpPress()
            Action.BackPress -> processBackPress()
        }

        currentJob?.cancel()
        currentJob = MainScope().launch {
            newStateFlow.collect {
                emitState(it)
            }
        }
    }

    private fun emitState(newState: ViewState?) {
        if (newState != null && newState != mutableStateFlow.value) {
            mutableStateFlow.value = newState

            val currentNav = navigator.current
            if (currentNav != null && currentNav::class == newState::class) {
                navigator.replaceCurrent(newState)
            } else {
                navigator.navigateTo(newState)
            }

            check(navigator.current == stateFlow.value) {
                "Navigator and stateFlow have mismatching states!"
            }
        }
    }

    private fun processCategorySelection(selectCategory: Action.SelectCategory): Flow<ViewState?> {
        return observeCocktailsByCategory(selectCategory.drinkCategory)
            .onStart { updateCocktailsByCategory(selectCategory.drinkCategory) }
            .mapLatest { ViewState.CocktailList(it) }
    }

    private fun processDrinkSelection(selectDrink: Action.SelectDrink): Flow<ViewState?> {
        return observeCocktailDetailsById(selectDrink.drinkId)
            .onStart { updateCocktailDetailsById(selectDrink.drinkId) }
            .mapLatest { ViewState.DrinkDetails(it) }
    }

    private fun processIngredientSelection(selectIngredient: Action.SelectIngredient): Flow<ViewState?> {
        return observeIngredientDetailsByName(selectIngredient.strIngredient)
            .onStart { updateIngredientDetailsByName(selectIngredient.strIngredient) }
            .mapLatest { ViewState.IngredientDetails(it, emptyList()) }
    }

    private fun processUpPress(): Flow<ViewState?> {
        return flowOf(navigator.back())
    }

    private fun processBackPress(): Flow<ViewState?> {
        // TODO: allow users to back all the way out of the app!
        return flowOf(navigator.back())
    }
}