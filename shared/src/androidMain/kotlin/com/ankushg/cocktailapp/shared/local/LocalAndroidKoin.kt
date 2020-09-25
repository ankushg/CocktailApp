package com.ankushg.cocktailapp.shared.local

import com.ankushg.cocktailapp.CocktailsDb
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

actual val provideSqlDriver: Scope.() -> SqlDriver = {
    AndroidSqliteDriver(
        schema = CocktailsDb.Schema,
        context = get(),
        name = "CocktailDb"
    )
}