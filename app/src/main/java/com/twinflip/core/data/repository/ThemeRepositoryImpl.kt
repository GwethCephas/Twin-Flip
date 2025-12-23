package com.twinflip.core.data.repository

import com.twinflip.core.data.datasource.ThemeProvider
import com.twinflip.core.data.mapper.toDomainTheme
import com.twinflip.core.data.persistence.datastore.ThemeDatastore
import com.twinflip.core.domain.model.Theme
import com.twinflip.core.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class ThemeRepositoryImpl(
    private val themeProvider: ThemeProvider,
    private val themeDatastore: ThemeDatastore
) : ThemeRepository {

    override fun getThemes(): List<Theme> {
        return themeProvider.getThemes().map { it.toDomainTheme() }
    }

    override suspend fun unlockTheme(themeName: String) {
        themeDatastore.unlockTheme(themeName, true)
    }

    override fun isThemeUnlocked(themeName: String): Flow<Boolean> {
        return themeDatastore.isThemeUnlocked(themeName)
    }
}