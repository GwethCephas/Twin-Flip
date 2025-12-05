package com.twinflip.presentation.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twinflip.domain.model.Theme
import com.twinflip.domain.usecase.ThemesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val themesUseCase: ThemesUseCase
) : ViewModel() {

    private var _themeUiState = MutableStateFlow(ThemeUiState())
    val themeUiState = _themeUiState.asStateFlow()

    init {
        loadThemes()
    }

    fun loadThemes() {
        viewModelScope.launch {
            _themeUiState.update { it.copy(isLoading = true) }

            val themes = themesUseCase.invoke()

            _themeUiState.update {
                it.copy(
                    isLoading = false,
                    themes = themes
                )
            }
        }
    }

}