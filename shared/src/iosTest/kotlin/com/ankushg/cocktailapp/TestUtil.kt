package com.ankushg.cocktailapp

import co.touchlab.sqliter.DatabaseConfiguration
import com.ankushg.cocktailapp.CocktailsDb
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

internal actual fun testDbConnection(): SqlDriver {
    val schema = CocktailsDb.Schema
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "cocktaildb",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = true
        )
    )
}
