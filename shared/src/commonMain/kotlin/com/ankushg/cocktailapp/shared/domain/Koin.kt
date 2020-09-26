package com.ankushg.cocktailapp.shared.domain

import com.ankushg.cocktailapp.shared.data.dataModule
import org.koin.dsl.module

val domainModule = module {
    factory { UpdateCocktailsByCategory(cocktailRepository = get()) }
    factory { ObserveCocktailsByCategory(cocktailRepository = get()) }

    factory { UpdateCocktailDetailsById(cocktailRepository = get()) }
    factory { ObserveCocktailDetailsById(cocktailRepository = get()) }

    factory { UpdateIngredientDetailsByName(ingredientRepository = get()) }
    factory { ObserveIngredientDetailsByName(ingredientRepository = get()) }
} + dataModule