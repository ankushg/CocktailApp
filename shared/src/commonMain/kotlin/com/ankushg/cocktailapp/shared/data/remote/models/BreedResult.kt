package com.ankushg.cocktailapp.shared.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class BreedResult(
    val message: HashMap<String, List<String>>,
    var status: String
)
