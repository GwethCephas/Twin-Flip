package com.twinflip.feature_themes.data.local.mapper

import com.twinflip.core.data.mapper.toDomainCard
import com.twinflip.feature_themes.data.local.model.ThemeEntity
import com.twinflip.feature_themes.domain.model.Theme

fun ThemeEntity.toDomainTheme(): Theme {
    return Theme(
        themeName = this.themeName,
        images = images.map { it.toDomainCard() }
    )
}