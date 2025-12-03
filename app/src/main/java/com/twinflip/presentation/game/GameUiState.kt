package com.twinflip.presentation.game

data class GameUiState(
    val cards: List<GameCard> = emptyList(),
    val moves: Int = 0,
    val matchedPairs: Int = 0,
    val firstSelected: GameCard? = null,
    val secondSelected: GameCard? = null,
    val isComparing: Boolean = false,
    val gameCompleted: Boolean = false
)
