package com.ankushg.cocktailapp.shared

import android.content.SharedPreferences
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
            schema = CocktailsDb.Schema,
            context = get(),
            name = "CocktailDb"
        )
    }

    single<Settings> {
        AndroidSettings(delegate = get<SharedPreferences>())
    }

    val baseKermit = Kermit(LogcatLogger())
        .withTag("CocktailApp")

    factory<Kermit> { (tag: String?) -> if (tag != null) baseKermit.withTag(tag) else baseKermit }
}
