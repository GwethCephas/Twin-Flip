package com.twinflip.core.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.twinflip.core.data.model.ThemeEntity

object ThemeLoader {

    fun loadTheme(context: Context, themeName: String): ThemeEntity {
        val json = context.assets.open("metadata/$themeName.json")
            .bufferedReader().use { it.readText() }
        return Gson().fromJson(json, ThemeEntity::class.java)
    }

}