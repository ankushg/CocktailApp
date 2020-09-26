package com.ankushg.cocktailapp.shared.remote.mappers

import com.ankushg.cocktailapp.shared.domain.enums.AlcoholStatus
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory
import com.ankushg.cocktailapp.shared.domain.enums.Glass
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DrinkCategoryAsStringSerializer : KSerializer<DrinkCategory> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("DrinkCategory", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DrinkCategory) {
        encoder.encodeString(value.strCategory)
    }

    override fun deserialize(decoder: Decoder): DrinkCategory {
        val strCategory = decoder.decodeString()
        return DrinkCategory.forValue(strCategory)
    }
}

object AlcoholStatusAsStringSerializer : KSerializer<AlcoholStatus> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("AlcoholStatus", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: AlcoholStatus) {
        encoder.encodeString(value.strAlcoholic)
    }

    override fun deserialize(decoder: Decoder): AlcoholStatus {
        val strCategory = decoder.decodeString()
        return AlcoholStatus.forValue(strCategory)
    }
}

object GlassAsStringSerializer : KSerializer<Glass> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Glass", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Glass) {
        encoder.encodeString(value.strGlass)
    }

    override fun deserialize(decoder: Decoder): Glass {
        val strCategory = decoder.decodeString()
        return Glass.forValue(strCategory)
    }
}