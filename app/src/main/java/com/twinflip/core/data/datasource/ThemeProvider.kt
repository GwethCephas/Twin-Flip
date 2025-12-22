package com.twinflip.core.data.datasource

import android.content.Context

class ThemeProvider(private val context: Context) {
    fun getThemes() = listOf(
        Themes.animals(context),
        Themes.emojis(context),
        Themes.fantasy(context),
        Themes.foodSnacks(context),
        Themes.fruits(context),
        Themes.music(context),
        Themes.nature(context),
        Themes.shapes(context),
        Themes.space(context),
        Themes.sports(context),
        Themes.underwater(context),
        Themes.vehicles(context)
    )
}