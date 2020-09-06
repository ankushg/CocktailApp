package com.ankushg.cocktailapp.shared.data.remote

import com.ankushg.cocktailapp.shared.data.remote.models.BreedResult

interface KtorApi {
    suspend fun getJsonFromApi(): BreedResult
}
