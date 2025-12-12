package com.twinflip.presentation.common


fun calculateScore(
    moves: Int,
    time: Int,
    perfectScore: Int = 1000
): Int {
    val totalScore = 1000 - ((moves * 10) + (time * 5))

    return if (moves == 16 && time <= 30) {
        perfectScore
    } else {
        totalScore
    }
}