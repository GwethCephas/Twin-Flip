package com.twinflip.core.data.mapper

import com.twinflip.core.data.model.ThemeEntity
import com.twinflip.core.domain.model.Theme

fun ThemeEntity.toDomainTheme(): Theme {
    return Theme(
        themeName = this.themeName,
        images = images.map { it.toDomainCard() }
    )
}