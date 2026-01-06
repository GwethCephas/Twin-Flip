package com.twinflip.feature_multiplayer.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.twinflip.core.presentation.game.CardItem
import kotlinx.coroutines.delay

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun MultiplayerScreen(
    modifier: Modifier = Modifier,
    multiplayerViewModel: MultiplayerViewModel,
    themeName: String
) {

    val context = LocalContext.current

    val state by multiplayerViewModel.multiplayerUiState.collectAsStateWithLifecycle()
    val cardSize = (LocalConfiguration.current.screenWidthDp - 20) / 4


    LaunchedEffect(themeName) {
        multiplayerViewModel.loadGame(themeName)
        delay(300)
        multiplayerViewModel.startGame()
    }

    Column(
        modifier = modifier
            .fillMaxSize().background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(10.dp)
            .windowInsetsPadding(
                WindowInsets.statusBars
            )
            ,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            state.playerOne?.let {
                PlayerCardItem(
                    player = it
                )

            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            when (state.gamePhase) {
                is GamePhase.Error -> {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    multiplayerViewModel.loadGame(themeName)
                }
                GamePhase.Finished -> {

                }
                GamePhase.Idle -> {}
                GamePhase.InProgress -> {
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
                                    multiplayerViewModel.cardClicked(card)
                                },
                                cardSize = cardSize.dp
                            )
                        }

                    }
                }
                GamePhase.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

            }


        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            state.playerTwo?.let {
                PlayerCardItem(
                    player = it
                )
            }
        }

    }


}