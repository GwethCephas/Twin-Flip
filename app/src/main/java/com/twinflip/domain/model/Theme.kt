package com.twinflip.domain.model

data class Theme(
    val themeName: String = "",
    val images: List<CardData> = emptyList(),
    val isUnLocked: Boolean = false,
    val unlockCondition: UnlockCondition? = null
)


sealed class UnlockCondition() {
    data class Score(val score: Int): UnlockCondition()
    data class Moves(val moves: Int): UnlockCondition()
    data class Time(val time: Int): UnlockCondition()
}


