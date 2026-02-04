package com.twinflip.core.data.datasource

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.twinflip.core.data.model.ThemeEntity

object ThemeLoader {
    @SuppressLint("DiscouragedApi")
    fun loadTheme(context: Context, themeName: String): ThemeEntity {
        val json = context.assets.open("metadata/$themeName.json")
            .bufferedReader().use { it.readText() }
        val rawTheme = Gson().fromJson(json, ThemeEntity::class.java)

        return rawTheme
    }

}
