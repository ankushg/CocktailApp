package com.ankushg.cocktailapp.shared.data.remote

import com.ankushg.cocktailapp.shared.data.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.data.enums.Glass
import com.ankushg.cocktailapp.shared.data.remote.models.CocktailResponse
import com.ankushg.cocktailapp.shared.data.remote.models.IngredientDetailResponse
import com.ankushg.cocktailapp.shared.data.remote.models.IngredientNameResponse

/**
 * [Documentation](https://www.thecocktaildb.com/api.php)
 */
interface CocktailApi {
    // region cocktails
    /**
     * Lookup full cocktail details by id
     *
     * https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
     */
    suspend fun fetchFullCocktailDetails(id: Int): CocktailResponse

    /**
     * Search cocktail by name
     *
     * https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
     */
    suspend fun cocktailsByName(query: String): CocktailResponse

    /**
     * List all cocktails by first letter
     *
     * https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a
     */
    suspend fun cocktailsByFirstLetter(letter: String): CocktailResponse

    /**
     * Search by ingredient
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Gin
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Vodka
     */
    suspend fun cocktailsByIngredient(ingredient: String): CocktailResponse

    /**
     * Filter by multi-ingredient (only available to $2+ Patreon supporters)
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Dry_Vermouth,Gin,Anis
     */
    suspend fun cocktailsByIngredients(ingredient: Collection<String>): CocktailResponse

    /**
     * Filter by alcoholic
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic
     */
    suspend fun cocktailsByAlcoholicStatus(alcoholStatus: AlcoholStatus): CocktailResponse

    /**
     * Filter by Category
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail
     */
    suspend fun cocktailsByCategory(category: DrinkCategory): CocktailResponse

    /**
     * Filter by Glass
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?g=Cocktail_glass
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?g=Champagne_flute
     */
    suspend fun cocktailsByGlass(glass: Glass): CocktailResponse

    /**
     * Lookup a random cocktail
     *
     * https://www.thecocktaildb.com/api/json/v1/1/random.php
     */
    suspend fun fetchRandomCocktail(): CocktailResponse

    /**
     * Lookup a selection of 10 random cocktails (only available to $2+ Patreon supporters)
     *
     * https://www.thecocktaildb.com/api/json/v1/1/randomselection.php
     */
    suspend fun fetchRandomCocktails(): CocktailResponse

    /**
     *  List Popular cocktails (only available to $2+ Patreon supporters)
     *
     *  https://www.thecocktaildb.com/api/json/v1/1/popular.php
     */
    suspend fun fetchPopularCocktails(): CocktailResponse

    /**
     * List most latest cocktails (only available to $2+ Patreon supporters)
     *
     * https://www.thecocktaildb.com/api/json/v1/1/latest.php
     */
    suspend fun fetchLatestCocktails(): CocktailResponse
    // endregion

    // region ingredients

    /**
     *
     * https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list
     */
    suspend fun indexIngredients(): IngredientNameResponse

    /**
     * Search ingredient by name
     *
     * https://www.thecocktaildb.com/api/json/v1/1/search.php?i=vodka
     */
    suspend fun fetchIngredientByName(query: String): IngredientDetailResponse

    /**
     * Lookup ingredient by ID
     *
     * https://www.thecocktaildb.com/api/json/v1/1/lookup.php?iid=552
     */
    suspend fun fetchIngredientById(id: Int): IngredientDetailResponse

    // endregion

    // region fetch things currently represented as enums
    // /**
    //  *
    //  https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list
    //  */
    // suspend fun indexCategories(): CategoryResponse
    //
    // /**
    //  *
    //  https://www.thecocktaildb.com/api/json/v1/1/list.php?g=list
    //  */
    // suspend fun indexGlasses(): GlassResponse
    //
    // /**
    //  *
    //  https://www.thecocktaildb.com/api/json/v1/1/list.php?a=list
    //  */
    // suspend fun indexAlcoholStatus(): AlcoholStatusResponse
    // endregion
}
