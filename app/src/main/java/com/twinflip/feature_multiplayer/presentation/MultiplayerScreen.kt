package com.twinflip.feature_multiplayer.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.twinflip.R
import com.twinflip.core.audio.GameSound
import com.twinflip.core.audio.MusicManager
import com.twinflip.core.audio.SoundManager
import com.twinflip.feature_ads.banner.BannerAdView
import com.twinflip.feature_ads.interstitial.InterstitialProvider
import kotlinx.coroutines.delay

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun MultiplayerScreen(
    modifier: Modifier = Modifier,
    multiplayerViewModel: MultiplayerViewModel,
    themeName: String,
    backgroundImage: Int,
    onNavigateToHomeScreen: () -> Unit,
    soundManager: SoundManager,
    musicManager: MusicManager
) {

    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(Unit) {
        InterstitialProvider.loadAd(context)
    }

    LaunchedEffect(Unit) {
        musicManager.play(R.raw.sfx_kids_guitar, volume = 0.3f)
    }

    val state by multiplayerViewModel.multiplayerUiState.collectAsStateWithLifecycle()
    val cardSize = (LocalConfiguration.current.screenWidthDp - 20) / 4

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(themeName) {
        multiplayerViewModel.loadGame(themeName)
        delay(300)
        multiplayerViewModel.startGame()
    }


    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = backgroundImage),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .windowInsetsPadding(
                    WindowInsets.statusBars
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            when (state.gamePhase) {
                is GamePhase.Error -> {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    multiplayerViewModel.loadGame(themeName)
                }

                GamePhase.Finished -> {
                    LaunchedEffect(Unit) {
                        if (activity != null) {
                            InterstitialProvider.showAd(activity) {
                                isVisible = true
                                soundManager.playSound(GameSound.LEVEL_COMPLETE)
                            }
                        } else {
                            isVisible = true
                        }
                        musicManager.stopMusic()
                    }

                    if (isVisible) {
                        MpCompleteScreen(
                            state = state,
                            isVisible = isVisible,
                            onDismissRequest = {
                                musicManager.play(R.raw.sfx_kids_guitar, volume = 0.3f)
                                isVisible = false
                                multiplayerViewModel.loadGame(themeName)
                                multiplayerViewModel.startGame()
                                InterstitialProvider.loadAd(context)
                            },
                            onNavigateToHomeScreen = onNavigateToHomeScreen,
                            soundManager = soundManager
                        )
                    }
                }

                GamePhase.Idle -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Game will start soon...",
                            style = LocalTextStyle.current,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )

                    }
                }

                GamePhase.InProgress -> {
                    GameInProgress(
                        state = state,
                        multiplayerViewModel = multiplayerViewModel,
                        cardSize = cardSize,
                        soundManager = soundManager
                    )
                }

                GamePhase.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Game is loading...",
                            style = LocalTextStyle.current,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }

            }


        }
        BannerAdView(
            modifier = modifier.align(Alignment.BottomCenter)
        )
    }
}