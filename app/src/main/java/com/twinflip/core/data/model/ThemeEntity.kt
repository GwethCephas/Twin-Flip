package com.twinflip.core.data.model

data class ThemeEntity(
    val themeName: String,
    val backgroundImage: String = "",
    val images: List<CardEntity>
)