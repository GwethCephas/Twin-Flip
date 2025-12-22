package com.twinflip.core.data.mapper

import com.twinflip.core.data.model.CardEntity
import com.twinflip.core.domain.model.CardData

fun CardEntity.toDomainCard(): CardData {
    return CardData(
        imagePath = this.imagePath,
        name = this.name
    )
}