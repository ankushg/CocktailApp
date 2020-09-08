package com.ankushg.cocktailapp.shared

import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.shared.data.local.DatabaseHelper
import com.ankushg.cocktailapp.shared.data.remote.CocktailApi
import com.ankushg.cocktailapp.shared.data.remote.CocktailApiImpl
import com.ankushg.cocktailapp.shared.data.remote.DogApi
import com.ankushg.cocktailapp.shared.data.remote.DogApiImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.module

fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            platformModule,
            coreModule
        )
    }

    // Dummy initialization logic, making use of appModule declarations for demonstration purposes.
    val koin = koinApplication.koin
    val doOnStartup =
        koin.get<() -> Unit>() // doOnStartup is a lambda which is implemented in Swift on iOS side
    doOnStartup.invoke()

    val kermit = koin.get<Kermit> { parametersOf(null) }
    val appInfo =
        koin.get<AppInfo>() // AppInfo is a Kotlin interface with separate Android and iOS implementations
    kermit.v { "App Id ${appInfo.appId}" }

    return koinApplication
}

private val coreModule = module {
    single {
        DatabaseHelper(
            sqlDriver = get(),
            log = getWith("DatabaseHelper"),
            backgroundDispatcher = Dispatchers.Default
        )
    }
    single<DogApi> {
        DogApiImpl(
            log = getWith("DogApiImpl")
        )
    }
    single<CocktailApi> {
        CocktailApiImpl(
            log = getWith("CocktailApiImpl")
        )
    }
}

internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

expect val platformModule: Module
