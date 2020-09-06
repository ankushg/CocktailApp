package com.ankushg.cocktailapp.shared.data.remote

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.data.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.data.enums.Glass
import com.ankushg.cocktailapp.shared.data.remote.serializers.CocktailDetailResponse
import com.ankushg.cocktailapp.shared.data.remote.serializers.CocktailSummaryResponse
import com.ankushg.cocktailapp.shared.data.remote.serializers.IngredientDetailResponse
import com.ankushg.cocktailapp.shared.data.remote.serializers.IngredientNameResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom

class CocktailApiImpl(private val log: Kermit) : CocktailApi {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    log.v("Network") { message }
                }
            }

            level = LogLevel.INFO
        }
    }

    init {
        ensureNeverFrozen()
    }

    private fun HttpRequestBuilder.endpoint(path: String, vararg params: Pair<String, Any>) {
        url {
            takeFrom("${CocktailApi.baseUrl}/${CocktailApi.developerApiKey}")
            path(path)
            params.forEach { (key, value) ->
                parameter(key, value)
            }

        }
    }

    override suspend fun fetchFullCocktailDetails(id: Int): CocktailDetailResponse = client.get {
        endpoint(
            "lookup.php",
            "i" to id
        )
    }

    override suspend fun cocktailsByName(query: String): CocktailSummaryResponse = client.get {
        endpoint(
            "search.php",
            "s" to query
        )
    }

    override suspend fun cocktailsByFirstLetter(letter: String): CocktailSummaryResponse =
        client.get {
            endpoint(
                "search.php",
                "f" to letter
            )
        }

    override suspend fun cocktailsByIngredient(ingredient: Any): CocktailSummaryResponse =
        client.get {
            endpoint(
                "filter.php",
                "i" to ingredient
            )
        }

    override suspend fun cocktailsByIngredients(ingredient: Collection<Any>): CocktailSummaryResponse =
        client.get {
            endpoint(
                "filter.php",
                "i" to ingredient.joinToString(separator = ",")
            )

        }

    override suspend fun cocktailsByAlcoholicStatus(alcoholStatus: AlcoholStatus): CocktailSummaryResponse =
        client.get {
            endpoint(
                "filter.php",
                "a" to alcoholStatus.strAlcoholic
            )
        }

    override suspend fun cocktailsByCategory(category: DrinkCategory): CocktailSummaryResponse =
        client.get {
            endpoint(
                "filter.php",
                "c" to category.strCategory
            )
        }

    override suspend fun cocktailsByGlass(glass: Glass): CocktailSummaryResponse = client.get {
        endpoint(
            "filter.php",
            "g" to glass.strGlass
        )
    }

    override suspend fun fetchRandomCocktail(): CocktailSummaryResponse = client.get {
        endpoint("random.php")
    }

    override suspend fun fetchRandomCocktails(): CocktailSummaryResponse = client.get {
        endpoint("randomselection.php")
    }

    override suspend fun fetchPopularCocktails(): CocktailSummaryResponse = client.get {
        endpoint("popular.php")
    }

    override suspend fun fetchLatestCocktails(): CocktailSummaryResponse = client.get {
        endpoint("latest.php")
    }

    override suspend fun ingredientsByName(query: String): IngredientDetailResponse = client.get {
        endpoint(
            "search.php",
            "i" to query
        )
    }

    override suspend fun indexIngredients(): IngredientNameResponse = client.get {
        endpoint(
            "list.php",
            "i" to "list"
        )
    }

    override suspend fun fetchIngredient(id: Int): IngredientDetailResponse = client.get {
        endpoint(
            "lookup.php",
            "iid" to id
        )
    }
}