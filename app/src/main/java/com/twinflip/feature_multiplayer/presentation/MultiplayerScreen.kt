package com.twinflip.feature_multiplayer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.twinflip.core.presentation.game.CardItem

@Composable
fun MultiplayerScreen(
    modifier: Modifier = Modifier,
    multiplayerViewModel: MultiplayerViewModel,
    themeName: String
) {

    val state by multiplayerViewModel.multiplayerUiState.collectAsStateWithLifecycle()
    val cardSize = (LocalConfiguration.current.screenWidthDp - 20) / 4


    LaunchedEffect(themeName) {
        multiplayerViewModel.loadGame(themeName)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .windowInsetsPadding(
                WindowInsets.statusBars.union(
                    WindowInsets.navigationBars
                )
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            state.playerOne?.let {
                PlayerCardItem(
                    modifier = modifier,
                    player = it
                )

            }
            state.playerTwo?.let {
                PlayerCardItem(
                    modifier = modifier,
                    player = it
                )
            }
        }

    }


}