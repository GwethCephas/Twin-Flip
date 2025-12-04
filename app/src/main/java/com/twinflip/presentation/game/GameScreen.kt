package com.twinflip.presentation.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.twinflip.data.utils.Constants.ANIMALS
import com.twinflip.data.utils.Constants.FRUITS

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel
) {
    val state by viewModel.gameUiState.collectAsState()
    val cardSize = (LocalConfiguration.current.screenWidthDp - 47) / 4

    LaunchedEffect(Unit) {
        viewModel.loadGames(ANIMALS)
    }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    viewModel.loadGames(FRUITS)
                }
            ) {
                Text(
                    text = "Change Theme"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
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
        }
    }
}