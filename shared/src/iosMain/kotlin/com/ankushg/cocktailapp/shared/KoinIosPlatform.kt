package com.ankushg.cocktailapp.shared

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.NSLogLogger
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import kotlinx.coroutines.Dispatchers
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module

fun initKoinIos(
    doOnStartup: () -> Unit
): KoinApplication = initKoin(
    listOf(
        module {
            single { doOnStartup },
            single<MainScope> { MainScope(
                Dispatchers.Main,
                log = get())
            }
        }
    )
) { }

internal actual fun buildBaseKermit(): Kermit {
    return Kermit(NSLogLogger())
        .withTag("CocktailApp")
}

fun Koin.get(objCClass: ObjCClass, qualifier: Qualifier?, parameter: Any): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier) { parametersOf(parameter) }
}

fun Koin.get(objCClass: ObjCClass, parameter: Any): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, null) { parametersOf(parameter) }
}

fun Koin.get(objCClass: ObjCClass, qualifier: Qualifier?): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier, null)
}
