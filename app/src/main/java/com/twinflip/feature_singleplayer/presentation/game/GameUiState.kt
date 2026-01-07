package com.twinflip.feature_singleplayer.presentation.game

import com.twinflip.core.domain.game.GameCard

data class GameUiState(
    val cards: List<GameCard> = emptyList(),
    val moves: Int = 0,
    val time: String = "00:00",
    val score: Int = 0,
    val matchedPairs: Int = 0,
    val firstSelected: GameCard? = null,
    val secondSelected: GameCard? = null,
    val isComparing: Boolean = false,
    val gameCompleted: Boolean = false
)
