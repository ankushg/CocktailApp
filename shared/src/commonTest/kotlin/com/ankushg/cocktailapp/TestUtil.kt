package com.ankushg.cocktailapp

import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.shared.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.remote.CocktailApi
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.withTimeout
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

fun appStart(
    helper: DatabaseHelper,
    api: CocktailApi,
    log: Kermit
) {
    val coreModule = module {
        single { helper }
        single { api }
        single { log }
    }

    startKoin { modules(coreModule) }
}

fun appEnd() {
    stopKoin()
}

// Await with a timeout
suspend fun <T> Deferred<T>.await(timeoutMillis: Long) =
    withTimeout(timeoutMillis) { await() }

internal expect fun testDbConnection(): SqlDriver
