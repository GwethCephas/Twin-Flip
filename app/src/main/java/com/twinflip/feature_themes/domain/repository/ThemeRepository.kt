package com.twinflip.feature_themes.domain.repository

import com.twinflip.feature_themes.domain.model.Theme
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun getThemes(): List<Theme>
    suspend fun unlockTheme(themeName: String)
    fun isThemeUnlocked(themeName: String): Flow<Boolean>

}