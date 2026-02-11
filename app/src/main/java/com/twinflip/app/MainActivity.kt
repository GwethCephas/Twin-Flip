package com.twinflip.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.twinflip.app.navigation.NavGraph
import com.twinflip.core.audio.MusicManager
import com.twinflip.core.audio.SoundManager
import com.twinflip.core.ui.theme.TwinFlipTheme
import com.twinflip.feature_multiplayer.presentation.MultiplayerViewModel
import com.twinflip.feature_singleplayer.presentation.game.GameViewModel
import com.twinflip.feature_themes.presentation.ThemeViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private lateinit var musicManager: MusicManager
    private lateinit var soundManager: SoundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                scrim = 0,
                darkScrim = 0
            )
        )

        musicManager = MusicManager(applicationContext)
        soundManager = SoundManager(applicationContext)

        setContent {
            TwinFlipTheme {
                val gameViewModel = koinViewModel<GameViewModel>()
                val themeViewModel = koinViewModel<ThemeViewModel>()
                val multiplayerViewModel = koinViewModel<MultiplayerViewModel>()

                NavGraph(
                    themeViewModel = themeViewModel,
                    gameViewModel = gameViewModel,
                    multiplayerViewModel = multiplayerViewModel,
                    soundManager = soundManager,
                    musicManager = musicManager
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        musicManager.resume()
    }
    override fun onPause() {
        super.onPause()
        musicManager.pauseMusic()

    }
    override fun onStop() {
        super.onStop()
        musicManager.stopMusic()
    }
    override fun onDestroy() {
        super.onDestroy()
        soundManager.releaseSound()
        musicManager.release()
    }
}