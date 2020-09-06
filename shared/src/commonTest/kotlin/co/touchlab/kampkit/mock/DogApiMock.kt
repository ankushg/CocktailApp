package co.touchlab.kampkit.mock

import co.touchlab.karmok.MockManager
import com.ankushg.cocktailapp.shared.data.remote.DogApi
import com.ankushg.cocktailapp.shared.data.remote.models.BreedResult

class DogApiMock : DogApi {
    // Call recording provided by experimental library Karmok
    internal val mock = InnerMock()
    override suspend fun fetchBreeds(): BreedResult {
        return mock.getJsonFromApi.invokeSuspend({ fetchBreeds() }, listOf())
    }

    class InnerMock(delegate: Any? = null) : MockManager(delegate) {
        internal val getJsonFromApi = MockFunctionRecorder<DogApiMock, BreedResult>()
    }

    fun successResult(): BreedResult {
        val map = HashMap<String, List<String>>().apply {
            put("appenzeller", emptyList())
            put("australian", listOf("shepherd"))
        }
        return BreedResult(map, "success")
    }
}
