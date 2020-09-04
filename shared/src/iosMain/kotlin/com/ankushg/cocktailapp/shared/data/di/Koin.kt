package com.ankushg.cocktailapp.shared.data.di

import com.ankushg.cocktailsapp.CocktailsDb
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformDataModule: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(
            CocktailsDb.Schema,
            get()
        )
    }
}