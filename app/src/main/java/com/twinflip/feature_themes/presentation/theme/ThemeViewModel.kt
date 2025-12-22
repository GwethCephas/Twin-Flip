package com.twinflip.feature_themes.presentation.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twinflip.feature_themes.domain.usecase.ThemesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val themesUseCase: ThemesUseCase
) : ViewModel() {

    private var _themeUiState = MutableStateFlow(ThemeUiState())
    val themeUiState = _themeUiState.asStateFlow()

    init {
        observeThemes()
    }

    private fun observeThemes() {
        viewModelScope.launch {
            _themeUiState.update { it.copy(isLoading = true) }

            val themes = themesUseCase.invoke()

            val themeFlows = themes.mapIndexed { index, theme ->
                if (index == 0) {
                    flowOf(theme.copy(isUnLocked = true))
                } else {
                    themesUseCase.isThemeLocked(theme.themeName).map { isUnlocked ->
                        theme.copy(isUnLocked = isUnlocked)
                    }
                }
            }

            combine(themeFlows) { themeArray ->
                themeArray.toList()
            }.collect { updatedThemes ->
                _themeUiState.update {
                    it.copy(
                        isLoading = false,
                        themes = updatedThemes
                    )
                }
            }
        }
    }

    fun unlockNextTheme(themeName: String) {
        viewModelScope.launch {
            val currentThemes = _themeUiState.value.themes
            val currentTheme = currentThemes.indexOfFirst { it.themeName == themeName }

            if (currentTheme != -1 && currentTheme + 1 < currentThemes.size) {
                val nextTheme = currentThemes[currentTheme + 1]
                themesUseCase.invoke(nextTheme.themeName)
            }
        }
    }



}