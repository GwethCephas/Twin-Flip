package com.twinflip.core.domain.repository

import com.twinflip.core.domain.model.CardData

interface CardRepository {
    fun getCardsForTheme(theme: String): List<CardData>

    fun getRandomCards(): List<CardData>
}