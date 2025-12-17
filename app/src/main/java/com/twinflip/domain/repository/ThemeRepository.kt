package com.twinflip.domain.repository

import com.twinflip.domain.model.Theme
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun getThemes(): List<Theme>
    suspend fun unlockTheme(themeName: String)
    fun isThemeUnlocked(themeName: String): Flow<Boolean>

}