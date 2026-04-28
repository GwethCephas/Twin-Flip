package com.twinflip.feature_themes.presentation

import app.cash.turbine.test
import com.twinflip.core.domain.model.Theme
import com.twinflip.core.domain.usecase.ThemesUseCase
import com.twinflip.common.MainDispatcherRule
import io.mockk.clearAllMocks
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class ThemeViewModelTest {

    @get:Rule
    val mainDispatcherRule: MainDispatcherRule = MainDispatcherRule()

    private lateinit var themesUseCase: ThemesUseCase
    private lateinit var themeViewModel: ThemeViewModel

    private val mockThemes = listOf(
        Theme(themeName = "animals", isUnLocked = true),
        Theme(themeName = "fruits", isUnLocked = false),
        Theme(themeName = "nature", isUnLocked = false)
    )

    @Before
    fun setUp() {
        themesUseCase = mockk()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getThemeUiState should return the correct state and load themes`() = runTest {

        every { themesUseCase.invoke() } returns mockThemes

        every {
            themesUseCase.isThemeLocked("fruits")
        } returns flowOf(true)

        every {
            themesUseCase.isThemeLocked("nature")
        } returns flowOf(false)

        themeViewModel = ThemeViewModel(themesUseCase)

        advanceUntilIdle()

        themeViewModel.themeUiState.test {

            val successState = awaitItem()
            assertFalse(successState.isLoading)
            assertEquals(3, successState.themes.size)

            assertTrue(successState.themes.first { it.themeName == "fruits" }.isUnLocked)
            assertFalse(successState.themes.first { it.themeName == "nature" }.isUnLocked)

            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `unlockNextTheme should unlock the next theme`() = runTest {

        every { themesUseCase.invoke() } returns mockThemes

        every { themesUseCase.isThemeLocked("fruits") } returns flowOf(false)
        every { themesUseCase.isThemeLocked("nature") } returns flowOf(false)

        coEvery { themesUseCase.invoke("fruits") } returns Unit

        themeViewModel = ThemeViewModel(themesUseCase)

        themeViewModel.themeUiState
            .filter { !it.isLoading && it.themes.isNotEmpty() }
            .first()

        clearMocks(themesUseCase, answers = false, recordedCalls = true)

        themeViewModel.unlockNextTheme("animals")

        advanceUntilIdle()

        coVerify { themesUseCase.invoke("fruits") }
    }

}
