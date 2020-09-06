package com.ankushg.cocktailapp.shared.data.remote

import com.ankushg.cocktailapp.shared.data.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.data.enums.Glass
import com.ankushg.cocktailapp.shared.data.remote.models.CocktailDetailResponse
import com.ankushg.cocktailapp.shared.data.remote.models.CocktailSummaryResponse
import com.ankushg.cocktailapp.shared.data.remote.models.IngredientDetailResponse
import com.ankushg.cocktailapp.shared.data.remote.models.IngredientNameResponse

/**
 * [Documentation](https://www.thecocktaildb.com/api.php)
 */
interface CocktailApi {
    companion object {
        val baseUrl = "https://www.thecocktaildb.com/api/json/v1/"

        // TODO: replace with real key once I figure out how to store secrets
        val developerApiKey = "1"
    }

    // region cocktails
    /**
     * Lookup full cocktail details by id
     *
     * https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
     */
    suspend fun fetchFullCocktailDetails(id: Int): CocktailDetailResponse

    /**
     * Search cocktail by name
     *
     * https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
     */
    suspend fun cocktailsByName(query: String): CocktailSummaryResponse

    /**
     * List all cocktails by first letter
     *
     * https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a
     */
    suspend fun cocktailsByFirstLetter(letter: String): CocktailSummaryResponse

    /**
     * Search by ingredient
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Gin
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Vodka
     */
    suspend fun cocktailsByIngredient(ingredient: Any): CocktailSummaryResponse

    /**
     * Filter by multi-ingredient (only available to $2+ Patreon supporters)
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Dry_Vermouth,Gin,Anis
     */
    suspend fun cocktailsByIngredients(ingredient: Collection<Any>): CocktailSummaryResponse =
        throw UnsupportedOperationException()

    /**
     * Filter by alcoholic
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic
     */
    suspend fun cocktailsByAlcoholicStatus(alcoholStatus: AlcoholStatus): CocktailSummaryResponse

    /**
     * Filter by Category
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail
     */
    suspend fun cocktailsByCategory(category: DrinkCategory): CocktailSummaryResponse

    /**
     * Filter by Glass
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?g=Cocktail_glass
     *
     * https://www.thecocktaildb.com/api/json/v1/1/filter.php?g=Champagne_flute
     */
    suspend fun cocktailsByGlass(glass: Glass): CocktailSummaryResponse

    /**
     * Lookup a random cocktail
     *
     * https://www.thecocktaildb.com/api/json/v1/1/random.php
     */
    suspend fun fetchRandomCocktail(): CocktailSummaryResponse

    /**
     * Lookup a selection of 10 random cocktails (only available to $2+ Patreon supporters)
     *
     * https://www.thecocktaildb.com/api/json/v1/1/randomselection.php
     */
    suspend fun fetchRandomCocktails(): CocktailSummaryResponse =
        throw UnsupportedOperationException()

    /**
     *  List Popular cocktails (only available to $2+ Patreon supporters)
     *
     *  https://www.thecocktaildb.com/api/json/v1/1/popular.php
     */
    suspend fun fetchPopularCocktails(): CocktailSummaryResponse =
        throw UnsupportedOperationException()

    /**
     * List most latest cocktails (only available to $2+ Patreon supporters)
     *
     * https://www.thecocktaildb.com/api/json/v1/1/latest.php
     */
    suspend fun fetchLatestCocktails(): CocktailSummaryResponse =
        throw UnsupportedOperationException()
    // endregion

    // region ingredients
    /**
     * Search ingredient by name
     *
     * https://www.thecocktaildb.com/api/json/v1/1/search.php?i=vodka
     */
    suspend fun ingredientsByName(query: String): IngredientDetailResponse

    /**
     *
     * https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list
     */
    suspend fun indexIngredients(): IngredientNameResponse

    /**
     * Lookup ingredient by ID
     *
     * https://www.thecocktaildb.com/api/json/v1/1/lookup.php?iid=552
     */
    suspend fun fetchIngredient(id: Int): IngredientDetailResponse

    // endregion

    // region enums
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
