package com.twinflip.feature_multiplayer.presentation

sealed class GamePhase {
    object Idle : GamePhase()
    object Loading : GamePhase()
    object InProgress : GamePhase()
    object Finished : GamePhase()
    data class Error(val message: String) : GamePhase()

}