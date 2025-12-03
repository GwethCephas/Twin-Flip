package com.twinflip.data.local.mapper

import com.twinflip.data.local.model.CardEntity
import com.twinflip.data.local.model.ThemeEntity
import com.twinflip.domain.model.CardData
import com.twinflip.domain.model.Theme

fun CardEntity.toDomainCard(): CardData {
    return CardData(
        imageRes = this.imageRes,
        name = this.name
    )
}

fun ThemeEntity.toDomainTheme(): Theme {
    return Theme(
        themeName = this.themeName,
        images = images.map { it.toDomainCard() }
    )
}