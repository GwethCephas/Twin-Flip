package com.twinflip.data.repository

import com.twinflip.data.local.datasource.ThemeProvider
import com.twinflip.data.local.mapper.toDomainTheme
import com.twinflip.data.local.persistence.datastore.ThemeDatastore
import com.twinflip.domain.model.Theme
import com.twinflip.domain.repository.ThemeRepository
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