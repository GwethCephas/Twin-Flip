package com.twinflip.data.repository

import com.twinflip.data.local.datasource.ThemeProvider
import com.twinflip.data.local.mapper.toDomainTheme
import com.twinflip.domain.model.CardData
import com.twinflip.domain.repository.CardRepository

class CardRepositoryImpl(
    private val themeProvider: ThemeProvider
) : CardRepository {
    override fun getCardsForTheme(theme: String): List<CardData> {
        return themeProvider.getThemes()
            .find { it.themeName.equals(theme, ignoreCase = true) }
            ?.toDomainTheme()?.images ?: emptyList()
    }

    override fun getRandomCards(): List<CardData> {
        return themeProvider.getThemes().random().toDomainTheme().images
    }
}