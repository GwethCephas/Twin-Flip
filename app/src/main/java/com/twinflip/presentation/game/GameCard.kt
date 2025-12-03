package com.twinflip.presentation.game

import com.twinflip.domain.model.CardData

data class GameCard(
    val id: Int,
    val card: CardData,
    val isFlipped: Boolean = false,
    val isMatched: Boolean = false
)
