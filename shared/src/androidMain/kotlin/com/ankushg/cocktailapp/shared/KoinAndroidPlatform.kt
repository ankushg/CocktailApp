package com.ankushg.cocktailapp.shared

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoinAndroid(
    appModule: Module,
    doOnStartup: (() -> Unit)? = null,
    appDeclaration: KoinAppDeclaration
): KoinApplication = initKoin(
    listOf(appModule) + buildAndroidKoinModule(doOnStartup),
    appDeclaration
)

internal fun buildAndroidKoinModule(
    doOnStartup: (() -> Unit)?
): Module {
    return module {
        single { doOnStartup }
    }
}

internal actual fun buildBaseKermit(): Kermit {
    return Kermit(LogcatLogger())
        .withTag("CocktailApp")
}