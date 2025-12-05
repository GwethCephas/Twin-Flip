package com.twinflip.data.repository

import com.twinflip.data.local.datasource.ThemeProvider
import com.twinflip.data.local.mapper.toDomainTheme
import com.twinflip.domain.model.Theme
import com.twinflip.domain.repository.ThemeRepository

class ThemeRepositoryImpl(
    private val themeProvider: ThemeProvider
) : ThemeRepository {

    override fun getThemes(): List<Theme> {
        return themeProvider.getThemes().map { it.toDomainTheme() }
    }

}