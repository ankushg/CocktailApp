package com.ankushg.cocktailapp.shared.domain

import com.ankushg.cocktailapp.shared.data.repositories.CocktailRepository
import com.ankushg.cocktailapp.shared.domain.entities.CocktailWithRecipe
import com.ankushg.cocktailapp.shared.domain.entities.recipeIngredients
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveCocktailDetailsById(
    private val cocktailRepository: CocktailRepository
) {
    operator fun invoke(drinkId: Long): Flow<CocktailWithRecipe> {
        return cocktailRepository.selectCocktailById(drinkId)
            .map { cocktail ->
                CocktailWithRecipe(
                    cocktail,
                    cocktail.recipeIngredients
                )
            }
    }
}