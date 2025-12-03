package com.twinflip.domain.usecase

import com.twinflip.domain.model.CardData
import com.twinflip.domain.repository.CardRepository

class CardsUseCase(
    private val cardRepository: CardRepository
) {
    operator fun invoke(theme: String): List<CardData> {
        return cardRepository.getCardsForTheme(theme)
    }

}