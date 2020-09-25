package com.ankushg.cocktailapp.shared.local

import com.ankushg.cocktailapp.CocktailsDb
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.core.scope.Scope

actual val provideSqlDriver: Scope.() -> SqlDriver = {
    NativeSqliteDriver(
        CocktailsDb.Schema,
        "CocktailDb"
    )
}