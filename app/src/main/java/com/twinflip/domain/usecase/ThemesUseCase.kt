package com.twinflip.domain.usecase

import com.twinflip.domain.model.Theme
import com.twinflip.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class ThemesUseCase(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke(): List<Theme> {
        return themeRepository.getThemes()
    }

    suspend operator fun invoke(themeName: String) {
        themeRepository.unlockTheme(themeName)
    }
    fun isThemeLocked(themeName: String): Flow<Boolean> {
        return themeRepository.isThemeUnlocked(themeName)
    }


}