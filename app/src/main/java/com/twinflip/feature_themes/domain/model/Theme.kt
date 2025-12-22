package com.twinflip.feature_themes.domain.model

import com.twinflip.core.domain.model.CardData

data class Theme(
    val themeName: String = "",
    val images: List<CardData> = emptyList(),
    val isUnLocked: Boolean = false
)
