package com.ankushg.cocktailapp

import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.shared.initKoinIos
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.check.checkModules
import kotlin.test.AfterTest
import kotlin.test.Test

class KoinTest : BaseTest() {
    @Test
    fun checkAllModules() {
        initKoinIos(
            doOnStartup = { }
        ).checkModules {
            create<Kermit> { parametersOf("TestTag") }
        }
    }

    @AfterTest
    fun breakdown() {
        stopKoin()
    }
}
