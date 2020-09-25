package com.ankushg.cocktailapp.shared.remote

import com.ankushg.cocktailapp.shared.getWith
import org.koin.dsl.module

val remoteModule = module {
    single<CocktailApi> {
        CocktailApiImpl(
            log = getWith("CocktailApiImpl")
        )
    }
}