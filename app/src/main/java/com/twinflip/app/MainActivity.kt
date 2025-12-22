package com.twinflip.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.twinflip.core.presentation.game.GameViewModel
import com.twinflip.app.navigation.NavGraph
import com.twinflip.feature_themes.presentation.theme.ThemeViewModel
import com.twinflip.core.ui.theme.TwinFlipTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.Companion.light(
                scrim = 0,
                darkScrim = 0
            )
        )
        setContent {
            TwinFlipTheme {
                val gameViewModel = koinViewModel<GameViewModel>()
                val themeViewModel = koinViewModel<ThemeViewModel>()

                NavGraph(
                    themeViewModel = themeViewModel,
                    gameViewModel = gameViewModel
                )
            }
        }
    }
}