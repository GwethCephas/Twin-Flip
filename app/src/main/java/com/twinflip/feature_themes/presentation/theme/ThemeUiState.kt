package com.twinflip.feature_themes.presentation.theme

import com.twinflip.core.domain.model.Theme

data class ThemeUiState(
    val isLoading: Boolean = false,
    val themes: List<Theme> = emptyList()
)
