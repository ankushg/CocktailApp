package com.ankushg.cocktailapp.shared.domain

import com.ankushg.cocktailapp.shared.data.repositories.CocktailRepository

class UpdateCocktailDetailsById(
    private val cocktailRepository: CocktailRepository
) {
    suspend operator fun invoke(drinkId: Long) =
        cocktailRepository.updateCocktailDetailsById(drinkId)
}