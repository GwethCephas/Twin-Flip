package com.twinflip.feature_multiplayer.model

import java.util.UUID

data class Player(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val score: Int = 0,
    val matchedPairs: Int = 0,
    val isActive: Boolean
)
