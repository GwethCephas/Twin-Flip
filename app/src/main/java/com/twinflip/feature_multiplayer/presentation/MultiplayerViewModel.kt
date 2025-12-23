package com.twinflip.feature_multiplayer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twinflip.core.domain.game.GameCard
import com.twinflip.core.domain.game.GameEngine
import com.twinflip.feature_multiplayer.model.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MultiplayerViewModel(
    private val gameEngine: GameEngine
) : ViewModel() {

    private var _multiplayerUiState = MutableStateFlow(MultiplayerUiState())
    val multiplayerUiState = _multiplayerUiState.asStateFlow()

    fun loadGame(themeName: String) {
        _multiplayerUiState.update {
            it.copy(
                gamePhase = GamePhase.Loading
            )
        }

        try {
            viewModelScope.launch {
                val shuffledCards = gameEngine.loadGames(themeName)

                val playerOne = Player( name = "Player 1", isActive = true)
                val playerTwo = Player( name = "Player 2", isActive = false)

                _multiplayerUiState.update {
                    it.copy(
                        cards = shuffledCards,
                        playerOne = playerOne,
                        playerTwo = playerTwo,
                        activePlayerId = it.playerOne?.id.toString(),
                        turnNumber = 1,
                        isComparing = false,
                        firstSelected = null,
                        secondSelected = null,
                        gamePhase = GamePhase.Idle
                    )
                }
            }
        } catch (e: Exception) {
            _multiplayerUiState.update {
                it.copy(
                    gamePhase = GamePhase.Error(e.message ?: "Unknown error")
                )
            }
        }


    }

    fun startGame() {
        _multiplayerUiState.update {
            it.copy(
                gamePhase = GamePhase.InProgress
            )
        }
    }

    fun finishGame(winnerId: String) {
        _multiplayerUiState.update {
            it.copy(
                gamePhase = GamePhase.Finished,
                activePlayerId = null,
                turnNumber = 0,
                playerOne = null,
                playerTwo = null,
                winnerId = winnerId,
                isComparing = false,
                firstSelected = null,
                secondSelected = null
            )
        }

    }

    fun switchTurns() {
        val state = _multiplayerUiState.value
        if (state.gamePhase != GamePhase.InProgress) return

        val nextPlayer = if (state.activePlayerId == state.playerOne?.id.toString()) {
            state.playerTwo
        } else {
            state.playerOne

        }

        _multiplayerUiState.update {
            it.copy(
                activePlayerId = nextPlayer?.id.toString(),
                turnNumber = it.turnNumber + 1,
                playerOne = it.playerOne?.copy(isActive = nextPlayer?.id.toString() == it.playerOne.id.toString()),
                playerTwo = it.playerTwo?.copy(isActive = nextPlayer?.id.toString() == it.playerTwo.id.toString())
            )
        }
    }

    fun cardClicked(card: GameCard) {
        val state = _multiplayerUiState.value

        if (state.isComparing) return

        if (card.isFlipped || card.isMatched) return

        if (state.firstSelected == null) {
            flipCard(card.id)
            _multiplayerUiState.update {
                it.copy(firstSelected = card)
            }
            return
        }
        if (state.secondSelected == null) {
            flipCard(card.id)
            _multiplayerUiState.update {
                it.copy(
                    secondSelected = card,
                    isComparing = true
                )
            }
            compareCards()
        }

    }

    private fun compareCards() {
        viewModelScope.launch {
            val state = _multiplayerUiState.value

            delay(800)

            val first = state.firstSelected!!
            val second = state.secondSelected!!


            if (first.card.name == second.card.name) {
                markAsMatched(first.id)
                markAsMatched(second.id)


                val newMatched = state.matchedPairs + 1

                _multiplayerUiState.update {
                    it.copy(
                        matchedPairs = newMatched,
                        gamePhase = if (newMatched == (state.cards.size / 2)) GamePhase.Finished else GamePhase.Idle
                    )
                }


            } else {
                flipCardBack(first.id)
                flipCardBack(second.id)
            }

            _multiplayerUiState.update {
                it.copy(
                    firstSelected = null,
                    secondSelected = null,
                    isComparing = false
                )
            }

        }
    }

    fun flipCard(id: Int) {
        _multiplayerUiState.update { state ->
            state.copy(
                cards = gameEngine.flipCard(state.cards, id)
            )
        }
    }

    fun flipCardBack(id: Int) {
        _multiplayerUiState.update { state ->
            state.copy(
                cards = gameEngine.flipCardBack(state.cards, id)
            )
        }
    }

    fun markAsMatched(id: Int) {
        _multiplayerUiState.update { state ->
            state.copy(
                cards = gameEngine.markAsMatched(state.cards, id)
            )
        }
    }

}


