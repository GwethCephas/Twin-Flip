package com.twinflip.data.repository

import android.util.Log
import com.twinflip.data.local.datasource.ThemeProvider
import com.twinflip.data.local.mapper.toDomainTheme
import com.twinflip.domain.model.CardData
import com.twinflip.domain.repository.CardRepository

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