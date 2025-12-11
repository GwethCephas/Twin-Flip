package com.twinflip.data.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.twinflip.data.local.model.ThemeEntity

object ThemeLoader {

    fun loadTheme(context: Context, themeName: String): ThemeEntity {
        val json = context.assets.open("metadata/$themeName.json")
            .bufferedReader().use { it.readText() }
        return Gson().fromJson(json, ThemeEntity::class.java)
    }

}