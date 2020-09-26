package com.ankushg.cocktailapp.shared.domain

import com.ankushg.cocktailapp.shared.data.repositories.CocktailRepository
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory

class ObserveCocktailsByCategory(
    private val cocktailRepository: CocktailRepository
) {
    operator fun invoke(category: DrinkCategory) =
        cocktailRepository.selectCocktailsByCategory(category)
}