package com.twinflip.core.presentation.game

import com.twinflip.core.domain.model.CardData

data class GameCard(
    val id: Int,
    val card: CardData,
    val isFlipped: Boolean = false,
    val isMatched: Boolean = false
)
