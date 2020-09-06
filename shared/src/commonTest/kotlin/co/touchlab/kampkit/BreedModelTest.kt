package co.touchlab.kampkit

import co.touchlab.kampkit.mock.DogApiMock
import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.BaseTest
import com.ankushg.cocktailapp.appEnd
import com.ankushg.cocktailapp.appStart
import com.ankushg.cocktailapp.shared.currentTimeMillis
import com.ankushg.cocktailapp.shared.data.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.data.repositories.BreedRepository
import com.ankushg.cocktailapp.testDbConnection
import com.russhwolf.settings.MockSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BreedModelTest : BaseTest() {

    private lateinit var repository: BreedRepository
    private val kermit = Kermit()
    private var dbHelper = DatabaseHelper(
        testDbConnection(),
        kermit,
        Dispatchers.Default
    )
    private val settings = MockSettings()
    private val ktorApi = DogApiMock()

    @BeforeTest
    fun setup() = runTest {
        appStart(dbHelper, settings, ktorApi, kermit)
        dbHelper.deleteAllBreeds()
        repository = BreedRepository()
        repository.selectAllBreeds().first()
    }

    @Test
    fun staleDataCheckTest() = runTest {
        settings.putLong(BreedRepository.DB_TIMESTAMP_KEY, currentTimeMillis())
        assertTrue(ktorApi.mock.getJsonFromApi.calledCount == 0)

        assertNull(repository.getBreedsFromNetwork())
        assertTrue(ktorApi.mock.getJsonFromApi.calledCount == 0)
    }

    @Test
    fun updateFavoriteTest() = runTest {
        ktorApi.mock.getJsonFromApi.returns(ktorApi.successResult())
        assertNull(repository.getBreedsFromNetwork())
        val breedOld = dbHelper.selectAllBreeds().first().first()
        assertFalse(breedOld.favorite == 1L)

        repository.updateBreedFavorite(breedOld)

        val breedNew = dbHelper.selectBreedsById(breedOld.id).first().first()
        assertEquals(breedNew.id, breedOld.id)
        assertTrue(breedOld.favorite == 1L)
    }

    @Test
    fun notifyErrorOnException() = runTest {
        ktorApi.mock.getJsonFromApi.throwOnCall(RuntimeException())

        assertNotNull(repository.getBreedsFromNetwork())
    }

    @AfterTest
    fun breakdown() = runTest {
        dbHelper.deleteAllBreeds()
        appEnd()
    }
}
