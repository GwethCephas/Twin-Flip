package com.twinflip.presentation.theme

import com.twinflip.domain.model.Theme

data class ThemeUiState(
    val isLoading: Boolean = false,
    val themes: List<Theme> = emptyList()
)
