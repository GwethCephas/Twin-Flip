package com.twinflip.feature_multiplayer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.twinflip.feature_singleplayer.presentation.game.CardItem

@Composable
fun GameInProgress(
    state: MultiplayerUiState,
    multiplayerViewModel: MultiplayerViewModel,
    cardSize: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        state.playerOne?.let {
            MpCardItem(
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
        horizontalArrangement = Arrangement.End
    ) {

        state.playerTwo?.let {
            MpCardItem(
                player = it
            )
        }
    }
}