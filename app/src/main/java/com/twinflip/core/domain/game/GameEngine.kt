package com.twinflip.core.domain.game

import com.twinflip.core.domain.usecase.CardsUseCase

class GameEngine(
    private val cardsUseCase: CardsUseCase
) {
    fun loadGames(themeName: String): List<GameCard> {
        val cards = cardsUseCase.invoke(themeName)

        val shuffledCards = (cards + cards)
            .shuffled()
            .mapIndexed { index, card ->
                GameCard(
                    id = index,
                    card = card
                )
            }
        return shuffledCards
    }
    fun flipCard(cards: List<GameCard>, id: Int): List<GameCard> =
        cards.map { if (it.id == id) it.copy(isFlipped = true) else it }

    fun flipCardBack(cards: List<GameCard>, id: Int): List<GameCard> =
        cards.map { if (it.id == id) it.copy(isFlipped = false) else it }

    fun markAsMatched(cards: List<GameCard>, id: Int): List<GameCard> =
        cards.map { if (it.id == id) it.copy(isMatched = true) else it }

}