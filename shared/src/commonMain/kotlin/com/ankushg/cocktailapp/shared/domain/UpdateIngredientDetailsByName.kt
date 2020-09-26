package com.ankushg.cocktailapp.shared.domain

import com.ankushg.cocktailapp.shared.data.repositories.IngredientRepository

class UpdateIngredientDetailsByName(
    private val ingredientRepository: IngredientRepository
) {
    suspend operator fun invoke(ingredientName: String) =
        ingredientRepository.updateIngredient(ingredientName)
}