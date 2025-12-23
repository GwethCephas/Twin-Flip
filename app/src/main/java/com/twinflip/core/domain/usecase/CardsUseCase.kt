package com.twinflip.core.domain.usecase

import com.twinflip.core.domain.model.CardData
import com.twinflip.core.domain.repository.CardRepository

class CardsUseCase(
    private val cardRepository: CardRepository
) {
    operator fun invoke(theme: String): List<CardData> {
        return cardRepository.getCardsForTheme(theme)
    }

    operator fun invoke(): List<CardData> {
        return cardRepository.getRandomCards()
    }

}