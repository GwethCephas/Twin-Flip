package com.twinflip.feature_home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.twinflip.R
import com.twinflip.core.audio.GameSound
import com.twinflip.core.audio.MusicManager
import com.twinflip.core.audio.SoundManager
import com.twinflip.core.ui.common.CustomButton
import com.twinflip.feature_multiplayer.presentation.ThemePickerSheet
import com.twinflip.feature_themes.presentation.ThemeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToThemeScreen: () -> Unit,
    onNavigateToMultiPlayerScreen: (String) -> Unit,
    themeViewModel: ThemeViewModel,
    soundManager: SoundManager,
    musicManager: MusicManager
) {

    var isThemePickerVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        musicManager.play(R.raw.sfx_kids_guitar, volume = 0.3f)
    }

    BackHandler(enabled = isThemePickerVisible) {
        isThemePickerVisible = false
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.twin_flip_mipmap_),
                contentDescription = "Twin Flip Logo"
            )
            CustomButton(
                text = "Single Player",
                color = MaterialTheme.colorScheme.primary,
                onClick = {
                    onNavigateToThemeScreen()
                },
                soundManager = soundManager
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                text = "Two Players",
                color = MaterialTheme.colorScheme.secondary,
                onClick = {
                    isThemePickerVisible = true
                },
                soundManager = soundManager
            )
        }
        ThemePickerSheet(
            themeViewModel = themeViewModel,
            isVisible = isThemePickerVisible,
            onNavigateToMultiplayer = { themeName ->
                soundManager.playSound(GameSound.BUTTON_TAP)
                scope.launch {
                    isThemePickerVisible = false
                    onNavigateToMultiPlayerScreen(themeName)
                }
            }
        )
    }
}