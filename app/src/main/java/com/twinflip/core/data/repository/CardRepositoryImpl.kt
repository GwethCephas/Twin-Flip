package com.twinflip.core.data.repository

import android.util.Log
import com.twinflip.core.domain.model.CardData
import com.twinflip.core.domain.repository.CardRepository
import com.twinflip.core.data.datasource.ThemeProvider
import com.twinflip.core.data.mapper.toDomainTheme

class CardRepositoryImpl(
    private val themeProvider: ThemeProvider
) : CardRepository {
    override fun getCardsForTheme(theme: String): List<CardData> {
      return try {
          themeProvider.getThemes()
              .find { it.themeName.equals(theme, ignoreCase = true) }
              ?.toDomainTheme()?.images ?: emptyList()

      } catch (e: Exception) {
          Log.e("CardRepositoryImpl", "Error getting cards for theme: $theme", e)
          emptyList()
      }

    }

    override fun getRandomCards(): List<CardData> {
        return try {
            themeProvider.getThemes().random().toDomainTheme().images
        } catch (e: Exception) {
            Log.e("CardRepositoryImpl", "Error getting random cards", e)
            emptyList()
        }
    }
}