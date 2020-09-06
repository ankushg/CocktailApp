package com.ankushg.cocktailapp.shared

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import com.ankushg.cocktailapp.CocktailsDb
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            CocktailsDb.Schema,
            get(),
            "CocktailDb"
        )
    }

    single<Settings> {
        AndroidSettings(get())
    }

    val baseKermit = Kermit(LogcatLogger()).withTag("KampKit")
    factory { (tag: String?) -> if (tag != null) baseKermit.withTag(tag) else baseKermit }
}
