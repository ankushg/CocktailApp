package com.ankushg.cocktailapp.shared.domain

import com.ankushg.cocktailapp.shared.data.repositories.CocktailRepository
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory

class UpdateCocktailsByCategory(
    private val cocktailRepository: CocktailRepository
) {
    suspend operator fun invoke(category: DrinkCategory) {
        cocktailRepository.updateCocktailsByCategory(category)
    }
}