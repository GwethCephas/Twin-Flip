package com.twinflip.data

import androidx.test.platform.app.InstrumentationRegistry
import com.twinflip.core.data.datasource.ThemeLoader
import com.twinflip.core.data.datasource.ThemeProvider
import okio.FileNotFoundException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ThemeLoaderTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun loadTheme() {

        val themeEntity = ThemeLoader.loadTheme(context, "animals")

        assertEquals(themeEntity.themeName, "animals")

    }

    @Test(expected = FileNotFoundException::class)
    fun load_missing_file() {
        ThemeLoader.loadTheme(context, "nonexistent")
    }

    @Test
    fun getThemes_returnsAllExpectedThemes() {
        val themeProvider = ThemeProvider(context)
        val themes = themeProvider.getThemes()

        assertEquals(themes.size, 12)

        assertTrue(themes.any { it.themeName == "vehicle" })

        assertEquals(themes.first().themeName, "animals")
        assertEquals(themes.last().themeName, "vehicle")

    }

}