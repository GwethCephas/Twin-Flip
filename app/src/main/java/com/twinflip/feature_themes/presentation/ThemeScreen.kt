package com.twinflip.feature_themes.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.twinflip.R
import com.twinflip.core.audio.GameSound
import com.twinflip.core.audio.MusicManager
import com.twinflip.core.audio.SoundManager
import com.twinflip.core.ui.common.CustomTopBar

@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeScreen(
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel,
    onNavigateToGame: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    soundManager: SoundManager,
    musicManager: MusicManager
) {
    val state by themeViewModel.themeUiState.collectAsState()

    val outerPadding = 20.dp

    LaunchedEffect(Unit) {
        musicManager.play(R.raw.sfx_kids_guitar, volume = 0.3f)
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
    ) {
        CustomTopBar(
            modifier = modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars),
            title = "Themes",
            onNavigateBack = {
                onNavigateToHome()
            }
        )
        Spacer(modifier = modifier.height(outerPadding))

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(24.dp),
            ) {
                Column(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "Choose a Theme",
                        style = MaterialTheme.typography.titleLarge
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        contentPadding = PaddingValues(
                            horizontal = 10.dp,
                            vertical = 5.dp
                        ),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(state.themes.size) { index ->
                            val theme = state.themes[index]
                            ThemeItem(
                                theme = theme,
                                onThemeClick = {
                                    soundManager.playSound(GameSound.BUTTON_TAP)
                                    onNavigateToGame(theme.themeName + "/${theme.backgroundImage}")
                                }
                            )
                        }
                    }
                }
            }
        }

    }

}