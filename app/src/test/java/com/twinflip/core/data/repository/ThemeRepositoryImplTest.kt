package com.twinflip.core.data.repository

import com.twinflip.core.data.datasource.ThemeProvider
import com.twinflip.core.data.persistence.datastore.ThemeDatastore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ThemeRepositoryImplTest {

    lateinit var themeProvider: ThemeProvider
    lateinit var themeDatastore: ThemeDatastore
    lateinit var repository: ThemeRepositoryImpl

    @Before
    fun setUp() {
        themeProvider = mock()
        themeDatastore = mock()

        repository = ThemeRepositoryImpl(themeProvider, themeDatastore)
    }


    @Test
    fun getThemes() {
        val themes = repository.getThemes()
        assertNotNull(themes)
    }

    @Test
    fun unlockTheme() = runTest {
        val themeName = "animals"

        repository.unlockTheme(themeName)

        verify(themeDatastore).unlockTheme(themeName, true)
    }

    @Test
    fun isThemeUnlocked() = runTest {
        val themeName = "animals"
        val expectedFlow = flowOf(true)

        whenever(themeDatastore.isThemeUnlocked(themeName)).thenReturn(expectedFlow)

        val actualFlow = themeDatastore.isThemeUnlocked(themeName)
        val isUnlocked = actualFlow.first()

        assertTrue(isUnlocked)


    }


}