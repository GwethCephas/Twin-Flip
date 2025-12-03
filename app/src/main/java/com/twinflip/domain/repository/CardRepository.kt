package com.twinflip.domain.repository

import com.twinflip.domain.model.CardData

interface CardRepository {
    fun getCardsForTheme(theme: String): List<CardData>

    fun getRandomCards(): List<CardData>
}