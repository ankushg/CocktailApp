package co.touchlab.kampkit

import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.BaseTest
import com.ankushg.cocktailapp.shared.data.local.DatabaseHelper
import com.ankushg.cocktailapp.testDbConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SqlDelightTest : BaseTest() {

    private lateinit var dbHelper: DatabaseHelper

    private suspend fun DatabaseHelper.insertBreed(name: String) {
        insertBreeds(listOf(name))
    }

    @BeforeTest
    fun setup() = runTest {
        dbHelper = DatabaseHelper(
            testDbConnection(),
            Kermit(),
            Dispatchers.Default
        )
        dbHelper.deleteAllBreeds()
        dbHelper.insertBreed("Beagle")
    }

    @Test
    fun `Select All Items Success`() = runTest {
        val breeds = dbHelper.selectAllBreeds().first()
        assertNotNull(
            breeds.find { it.name == "Beagle" },
            "Could not retrieve Breed"
        )
    }

    @Test
    fun `Select Item by Id Success`() = runTest {
        val breeds = dbHelper.selectAllBreeds().first()
        val firstBreed = breeds.first()
        assertNotNull(
            dbHelper.selectBreedsById(firstBreed.id),
            "Could not retrieve Breed by Id"
        )
    }

    @Test
    fun `Update Favorite Success`() = runTest {
        val breeds = dbHelper.selectAllBreeds().first()
        val firstBreed = breeds.first()
        dbHelper.updateBreedFavoriteStatus(firstBreed.id, true)
        val newBreed = dbHelper.selectBreedsById(firstBreed.id).first().first()
        assertNotNull(
            newBreed,
            "Could not retrieve Breed by Id"
        )
        assertTrue(
            newBreed.favorite == 1L,
            "Favorite Did Not Save"
        )
    }

    @Test
    fun `Delete All Success`() = runTest {
        dbHelper.insertBreed("Poodle")
        dbHelper.insertBreed("Schnauzer")
        assertTrue(dbHelper.selectAllBreeds().first().isNotEmpty())
        dbHelper.deleteAllBreeds()

        assertTrue(
            dbHelper.selectAllBreeds().first().count() == 0,
            "Delete All did not work"
        )
    }
}
