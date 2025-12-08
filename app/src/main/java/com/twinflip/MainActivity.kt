package com.twinflip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.twinflip.presentation.game.GameViewModel
import com.twinflip.presentation.navigation.NavGraph
import com.twinflip.presentation.theme.ThemeViewModel
import com.twinflip.ui.theme.TwinFlipTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
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

