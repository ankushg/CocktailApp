package com.ankushg.cocktailapp.shared

import co.touchlab.kermit.Kermit
import com.ankushg.cocktailapp.shared.app.AppViewModel
import com.ankushg.cocktailapp.shared.app.CommonAppViewModel
import com.ankushg.cocktailapp.shared.domain.domainModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

internal fun initKoin(
    platformModules: List<Module>,
    appDeclaration: KoinAppDeclaration
): KoinApplication {
    val koinApplication = startKoin {
        appDeclaration()

        modules(
            platformModules + commonSharedModule + domainModule
        )
    }

    // Dummy initialization logic, making use of appModule declarations for demonstration purposes.
    val koin = koinApplication.koin

    // doOnStartup is a lambda which can be optionally implemented
    val doOnStartup = koin.getOrNull<() -> Unit>()
    doOnStartup?.invoke()

    // Just logging
    val kermit = koin.get<Kermit> { parametersOf(null) }
    kermit.v { "Shared Koin initialized..." }

    return koinApplication
}

private val commonSharedModule = module {
    val baseKermit = buildBaseKermit()

    factory<Kermit> { (tag: String?) -> if (tag != null) baseKermit.withTag(tag) else baseKermit }

    single<AppViewModel> {
        CommonAppViewModel()
    }
}

internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

internal inline fun <reified T> KoinComponent.injectWith(vararg params: Any?): Lazy<T> {
    return inject(parameters = { parametersOf(*params) })
}

internal expect fun buildBaseKermit(): Kermit
