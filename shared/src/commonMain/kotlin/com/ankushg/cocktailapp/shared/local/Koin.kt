package com.ankushg.cocktailapp.shared.local

import com.ankushg.cocktailapp.shared.getWith
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.Dispatchers
import org.koin.core.scope.Scope
import org.koin.dsl.module

val localModule = module {
    single<SqlDriver> { provideSqlDriver(this) }
    single {
        DatabaseHelper(
            sqlDriver = get<SqlDriver>(),
            log = getWith("DatabaseHelper"),
            backgroundDispatcher = Dispatchers.Default
        )
    }
}

expect val provideSqlDriver: Scope.() -> SqlDriver