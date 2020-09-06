package com.ankushg.cocktailapp.shared.data.remote

import com.ankushg.cocktailapp.shared.data.remote.models.BreedResult

interface DogApi {
    suspend fun fetchBreeds(): BreedResult
}
