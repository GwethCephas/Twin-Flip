package com.twinflip.domain.repository

import com.twinflip.domain.model.Theme

interface ThemeRepository {
    fun getThemes(): List<Theme>

}