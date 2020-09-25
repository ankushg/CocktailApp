package com.ankushg.cocktailapp.shared.data

import com.ankushg.cocktailapp.shared.data.repositories.CocktailRepository
import com.ankushg.cocktailapp.shared.data.repositories.IngredientRepository
import com.ankushg.cocktailapp.shared.getWith
import com.ankushg.cocktailapp.shared.local.localModule
import com.ankushg.cocktailapp.shared.remote.remoteModule
import org.koin.dsl.module

val dataModule = module {
    single { CocktailRepository(get(), get(), log = getWith("CocktailRepository")) }
    single { IngredientRepository(get(), get(), log = getWith("IngredientRepository")) }
} + remoteModule + localModule