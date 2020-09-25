package com.ankushg.cocktailapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.shared.initKoin
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules
import kotlin.test.AfterTest
import kotlin.test.Test

@RunWith(AndroidJUnit4::class)
@Category(CheckModuleTest::class)
class KoinTest : BaseTest() {

    @Test
    fun checkAllModules() {
        initKoin(
            platformModules = listOf(
                module {}
            )
        ) {}
            .checkModules {
                create<Kermit> { parametersOf("TestTag") }
            }
    }

    @AfterTest
    fun breakdown() {
        stopKoin()
    }
}
