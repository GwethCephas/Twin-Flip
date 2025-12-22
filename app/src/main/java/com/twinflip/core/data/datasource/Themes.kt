package com.twinflip.core.data.datasource

import android.content.Context
import com.twinflip.feature_themes.data.utils.Constants.ANIMALS
import com.twinflip.feature_themes.data.utils.Constants.EMOJIS
import com.twinflip.feature_themes.data.utils.Constants.FANTASY
import com.twinflip.feature_themes.data.utils.Constants.FOODS
import com.twinflip.feature_themes.data.utils.Constants.FRUITS
import com.twinflip.feature_themes.data.utils.Constants.MUSIC
import com.twinflip.feature_themes.data.utils.Constants.NATURE
import com.twinflip.feature_themes.data.utils.Constants.SHAPES
import com.twinflip.feature_themes.data.utils.Constants.SPACE
import com.twinflip.feature_themes.data.utils.Constants.SPORTS
import com.twinflip.feature_themes.data.utils.Constants.WATER
import com.twinflip.feature_themes.data.utils.Constants.VEHICLES

object Themes {
    fun animals(context: Context) = ThemeLoader.loadTheme(context, ANIMALS)
    fun emojis(context: Context) = ThemeLoader.loadTheme(context, EMOJIS)
    fun fantasy(context: Context) = ThemeLoader.loadTheme(context, FANTASY)
    fun foodSnacks(context: Context) = ThemeLoader.loadTheme(context, FOODS)
    fun fruits(context: Context) = ThemeLoader.loadTheme(context, FRUITS)
    fun music(context: Context) = ThemeLoader.loadTheme(context, MUSIC)
    fun nature(context: Context) = ThemeLoader.loadTheme(context, NATURE)
    fun shapes(context: Context) = ThemeLoader.loadTheme(context, SHAPES)
    fun space(context: Context) = ThemeLoader.loadTheme(context, SPACE)
    fun sports(context: Context) = ThemeLoader.loadTheme(context, SPORTS)
    fun underwater(context: Context) = ThemeLoader.loadTheme(context, WATER)
    fun vehicles(context: Context) = ThemeLoader.loadTheme(context, VEHICLES)
}