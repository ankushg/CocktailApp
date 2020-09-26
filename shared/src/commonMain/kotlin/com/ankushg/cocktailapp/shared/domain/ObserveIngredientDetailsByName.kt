package com.ankushg.cocktailapp.shared.domain

import com.ankushg.cocktailapp.shared.data.repositories.IngredientRepository
import com.ankushg.cocktailapp.shared.domain.entities.DomainIngredient
import kotlinx.coroutines.flow.Flow

class ObserveIngredientDetailsByName(
    private val ingredientRepository: IngredientRepository
) {
    operator fun invoke(ingredientName: String): Flow<DomainIngredient> {
        return ingredientRepository.selectIngredientByName(ingredientName)
    }
}