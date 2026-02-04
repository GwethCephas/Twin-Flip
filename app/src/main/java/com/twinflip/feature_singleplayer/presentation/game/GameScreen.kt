package com.twinflip.feature_singleplayer.presentation.game

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.twinflip.R
import com.twinflip.core.presentation.common.calculateScore
import com.twinflip.feature_themes.presentation.ThemeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel,
    themeName: String,
    backgroundImage: Int,
    themeViewModel: ThemeViewModel,
    onNavigateBack: () -> Unit
) {

    val state by viewModel.gameUiState.collectAsState()
    val cardSize = (LocalConfiguration.current.screenWidthDp - 20) / 4

    LaunchedEffect(themeName) {
        viewModel.loadGames(themeName)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopTimer()
        }
    }

    LaunchedEffect(state.gameCompleted) {
        if (state.gameCompleted) {
            themeViewModel.unlockNextTheme(themeName)
            viewModel.stopTimer()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = backgroundImage),
            contentScale = ContentScale.Crop,
            contentDescription = "Background"
        )


        Column(
            modifier = modifier
                .fillMaxSize()
//                .background(MaterialTheme.colorScheme.tertiary),
            ,horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {


            if (state.gameCompleted) {

                val currentElapsedTime = state.time

                val currentScore = calculateScore(
                    state.moves,
                    parseTimeToSeconds(currentElapsedTime)
                )

                GameCompleteScreen(
                    time = currentElapsedTime,
                    moves = state.moves,
                    score = currentScore,
                    onNavigateToThemeScreen = onNavigateBack,
                    onPlayAgainClick = {
                        viewModel.loadGames(themeName)
                    }
                )

            } else {
                CenterAlignedTopAppBar(
                    modifier = modifier.windowInsetsPadding(WindowInsets.statusBars),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    title = {
                        Text(
                            text = themeName,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            fontWeight = SemiBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    navigationIcon = {
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colorScheme.background)
                                .clickable { onNavigateBack() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.outline_arrow_back_24),
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                    },
                    actions = {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(10.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                text = state.time,
                                fontSize = 15.sp,
                                fontWeight = SemiBold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    columns = GridCells.Fixed(4),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(state.cards.size) { index ->
                        val card = state.cards[index]
                        CardItem(
                            gameCard = card,
                            onCardClick = {
                                viewModel.cardClicked(card)
                            },
                            cardSize = cardSize.dp
                        )
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = MaterialTheme.colorScheme.secondary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                        text = "Matched pairs ${state.matchedPairs} |  Moves ${state.moves}",
                        fontSize = 16.sp,
                        fontWeight = SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }
        }
    }
}

fun parseTimeToSeconds(timeString: String): Int {
    val parts = timeString.split(":")
    if (parts.size != 2) return 0

    val minutes = parts[0].toIntOrNull() ?: 0
    val seconds = parts[1].toIntOrNull() ?: 0

    return (minutes * 60) + seconds
}
