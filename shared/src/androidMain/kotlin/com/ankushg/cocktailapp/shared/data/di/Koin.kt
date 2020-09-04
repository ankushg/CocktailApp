package com.ankushg.cocktailapp.shared.data.di

import com.ankushg.cocktailsapp.CocktailsDb
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformDataModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            CocktailsDb.Schema,
            get(),
            "CocktailDb"
        )
    }
}