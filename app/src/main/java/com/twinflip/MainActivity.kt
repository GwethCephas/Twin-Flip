package com.twinflip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.twinflip.presentation.game.GameScreen
import com.twinflip.presentation.game.GameViewModel
import com.twinflip.ui.theme.TwinFlipTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TwinFlipTheme {
                val gameViewModel = koinViewModel<GameViewModel>()
                GameScreen(viewModel = gameViewModel)
            }
        }
    }
}

