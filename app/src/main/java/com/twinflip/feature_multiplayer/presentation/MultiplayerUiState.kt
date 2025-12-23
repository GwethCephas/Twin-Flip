package com.twinflip.feature_multiplayer.presentation

import com.twinflip.core.domain.game.GameCard
import com.twinflip.feature_multiplayer.model.Player

data class MultiplayerUiState(
    val gamePhase: GamePhase = GamePhase.Idle,
    val cards: List<GameCard> = emptyList(),
    val playerOne: Player? = null,
    val playerTwo: Player? = null,
    val activePlayerId: String? = null,
    val turnNumber: Int = 0,
    val winnerId: String = "",
    val firstSelected: GameCard? = null,
    val secondSelected: GameCard? = null,
    val isComparing: Boolean = false,
    val matchedPairs: Int = 0
)
