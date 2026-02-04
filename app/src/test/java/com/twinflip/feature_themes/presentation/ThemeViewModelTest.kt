package com.twinflip.feature_themes.presentation

import app.cash.turbine.test
import com.twinflip.core.domain.model.Theme
import com.twinflip.core.domain.usecase.ThemesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class ThemeViewModelTest {

    lateinit var themesUseCase: ThemesUseCase
    lateinit var themeViewModel: ThemeViewModel

    private val mockThemes = listOf(
        Theme(themeName = "animals", isUnLocked = true),
        Theme(themeName = "fruits", isUnLocked = false),
        Theme(themeName = "vegetables", isUnLocked = false)
    )

    @Before
    fun setUp() {
        themesUseCase = mock()


    }

    @Test
    fun `getThemeUiState should return the correct state and load themes`() = runTest {

        whenever { themesUseCase.invoke() }.thenReturn(mockThemes)

        whenever(themesUseCase.isThemeLocked("fruits"))
            .thenReturn(flowOf(true))

        whenever(themesUseCase.isThemeLocked("vegetables"))
            .thenReturn(flowOf(false))

        themeViewModel = ThemeViewModel(themesUseCase)

        advanceUntilIdle()

        themeViewModel.themeUiState.test {
            val initial = awaitItem()
            assertFalse(initial.isLoading)
            assertTrue(initial.themes.isEmpty())

            val loading = awaitItem()
            assertTrue(loading.isLoading)

            val finalState = awaitItem()
            assertFalse(finalState.isLoading)
            assertEquals(3, finalState.themes.size)

            assertTrue(finalState.themes[0].isUnLocked)

            assertTrue(finalState.themes[1].isUnLocked)
            assertFalse(finalState.themes[2].isUnLocked)

            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `unlockNextTheme should unlock the next theme`() = runTest {

        whenever(themesUseCase.invoke()).thenReturn(mockThemes)

        whenever(themesUseCase.isThemeLocked("fruits"))
            .thenReturn(flowOf(false))

        whenever(themesUseCase.isThemeLocked("vegetables"))
            .thenReturn(flowOf(false))

        whenever(themesUseCase.invoke("fruits")).thenReturn(Unit)

        themeViewModel = ThemeViewModel(themesUseCase)

        themeViewModel.themeUiState
            .filter { it.themes.isNotEmpty() }
            .first()

        clearInvocations(themesUseCase)


        themeViewModel.unlockNextTheme("animals")

        advanceUntilIdle()

        verify(themesUseCase).invoke("fruits")
    }

}