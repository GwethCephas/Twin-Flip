package com.twinflip.feature_themes.data.local.model

import com.twinflip.core.data.model.CardEntity

data class ThemeEntity(
    val themeName: String,
    val images: List<CardEntity>
)
