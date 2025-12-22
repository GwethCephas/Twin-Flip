package com.twinflip.core.data.persistence.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.datastore by preferencesDataStore("theme_prefs")

class ThemeDatastore(
    private val context: Context
) {
    private fun themeKey(themeName: String) = booleanPreferencesKey("theme_locked_$themeName}")

    fun isThemeUnlocked(themeName: String): Flow<Boolean> {
        return context.datastore.data
            .catch { e ->
                if (e is IOException) {
                    Log.e("ThemeDatastore", "Error reading preferences", e)
                    emit(emptyPreferences())
                }
            }
            .map { prefs ->
                prefs[themeKey(themeName)] ?: false
            }

    }

    suspend fun unlockTheme(themeName: String, isUnlocked: Boolean) {
        context.datastore.edit { prefs ->
            prefs[themeKey(themeName)] = isUnlocked
        }
    }
}