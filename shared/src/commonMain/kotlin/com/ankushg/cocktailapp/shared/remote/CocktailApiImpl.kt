package com.ankushg.cocktailapp.shared.remote

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.ankushg.cocktailapp.shared.domain.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.domain.enums.Glass
import com.ankushg.cocktailapp.shared.remote.models.CocktailResponse
import com.ankushg.cocktailapp.shared.remote.models.CocktailSummaryResponse
import com.ankushg.cocktailapp.shared.remote.models.IngredientDetailResponse
import com.ankushg.cocktailapp.shared.remote.models.IngredientNameResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLBuilder
import io.ktor.http.encodeURLQueryComponent
import io.ktor.http.takeFrom

class CocktailApiImpl(private val log: Kermit) : CocktailApi {
    companion object {
        private val baseUrl = "https://www.thecocktaildb.com/api/json/v1/"

        // TODO(https://github.com/ankushg/CocktailApp/issues/1): replace with real API key
        private val developerApiKey = "1"

        private fun URLBuilder.appendToPath(vararg components: String) {
            // removes all trailing and leading separators
            val paths = components
                .map { it.replace(Regex("""(^/|/+$)"""), "") }
                .joinToString("/", transform = { it.encodeURLQueryComponent() })
            // make sure that there's a slash separator at the end of current path
            if (!encodedPath.endsWith('/')) {
                encodedPath = "${encodedPath}/"
            }
            this.encodedPath += paths
        }

        private fun HttpRequestBuilder.endpoint(path: String, vararg params: Pair<String, Any>) {
            url {
                takeFrom("$baseUrl/$developerApiKey")
                appendToPath(path)
                params.forEach { (key, value) ->
                    parameter(key, value)
                }
            }
        }
    }

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

    override suspend fun fetchFullCocktailDetails(id: Long): CocktailResponse = client.get {
        log.d { "Querying API for cocktail with ID $id" }

        endpoint(
            "lookup.php",
            "i" to id
        )
    }

    override suspend fun cocktailsByName(name: String): CocktailResponse = client.get {
        log.d { "Querying API for cocktail details: $name" }

        endpoint(
            "search.php",
            "s" to name
        )
    }

    override suspend fun cocktailsByFirstLetter(letter: String): CocktailResponse =
        client.get {
            log.d { "Querying API for cocktails starting with: $letter" }

            endpoint(
                "search.php",
                "f" to letter
            )
        }

    override suspend fun cocktailsByIngredient(ingredient: String): CocktailSummaryResponse =
        client.get {
            log.d { "Querying API for cocktails containing: $ingredient" }

            endpoint(
                "filter.php",
                "i" to ingredient
            )
        }

    override suspend fun cocktailsByIngredients(ingredientList: Collection<String>): CocktailSummaryResponse =
        client.get {
            log.d { "Querying API for cocktails containing: $ingredientList" }

            endpoint(
                "filter.php",
                "i" to ingredientList.joinToString(separator = ",")
            )
        }

    override suspend fun cocktailsByAlcoholicStatus(alcoholStatus: AlcoholStatus): CocktailSummaryResponse =
        client.get {
            log.d { "Querying API for cocktails by alcohol status: $alcoholStatus" }

            endpoint(
                "filter.php",
                "a" to alcoholStatus.strAlcoholic
            )
        }

    override suspend fun cocktailsByCategory(category: DrinkCategory): CocktailSummaryResponse =
        client.get {
            log.d { "Querying API for cocktails by category: $category" }

            endpoint(
                "filter.php",
                "c" to category.strCategory
            )
        }

    override suspend fun cocktailsByGlass(glass: Glass): CocktailSummaryResponse = client.get {
        log.d { "Querying API for cocktails by glass type: $glass" }

        endpoint(
            "filter.php",
            "g" to glass.strGlass
        )
    }

    override suspend fun fetchRandomCocktail(): CocktailResponse = client.get {
        log.d { "Querying API for random cocktail" }

        endpoint("random.php")
    }

    override suspend fun fetchRandomCocktailSelection(): CocktailResponse = client.get {
        log.d { "Querying API for random selection of cocktails" }

        endpoint("randomselection.php")
    }

    override suspend fun fetchPopularCocktails(): CocktailResponse = client.get {
        log.d { "Querying API for popular cocktails" }

        endpoint("popular.php")
    }

    override suspend fun fetchLatestCocktails(): CocktailResponse = client.get {
        log.d { "Querying API for latest cocktails" }

        endpoint("latest.php")
    }

    override suspend fun fetchIngredientByName(name: String): IngredientDetailResponse =
        client.get {
            log.d { "Querying API for ingredient details: $name" }

            endpoint(
                "search.php",
                "i" to name
            )
        }

    override suspend fun indexIngredients(): IngredientNameResponse = client.get {
        log.d { "Querying API for all ingredients..." }

        endpoint(
            "list.php",
            "i" to "list"
        )
    }

    override suspend fun fetchIngredientById(id: Int): IngredientDetailResponse = client.get {
        log.d { "Querying API for ingredient with ID $id" }

        endpoint(
            "lookup.php",
            "iid" to id
        )
    }
}
