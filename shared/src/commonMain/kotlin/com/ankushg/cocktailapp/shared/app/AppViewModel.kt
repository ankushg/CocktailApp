package com.ankushg.cocktailapp.shared.app

import kotlinx.coroutines.flow.StateFlow

interface AppViewModel {
    val stateFlow: StateFlow<ViewState?>
    fun submitAction(action: Action)
}