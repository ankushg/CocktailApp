package co.touchlab.kampkit.mock

import co.touchlab.karmok.MockManager
import com.ankushg.cocktailapp.shared.data.remote.KtorApi
import com.ankushg.cocktailapp.shared.data.remote.models.BreedResult

class KtorApiMock : KtorApi {
    // Call recording provided by experimental library Karmok
    internal val mock = InnerMock()
    override suspend fun getJsonFromApi(): BreedResult {
        return mock.getJsonFromApi.invokeSuspend({ getJsonFromApi() }, listOf())
    }

    class InnerMock(delegate: Any? = null) : MockManager(delegate) {
        internal val getJsonFromApi = MockFunctionRecorder<KtorApiMock, BreedResult>()
    }

    fun successResult(): BreedResult {
        val map = HashMap<String, List<String>>().apply {
            put("appenzeller", emptyList())
            put("australian", listOf("shepherd"))
        }
        return BreedResult(map, "success")
    }
}
