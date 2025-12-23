package com.twinflip.core.domain.model

data class Theme(
    val themeName: String = "",
    val images: List<CardData> = emptyList(),
    val isUnLocked: Boolean = false
)