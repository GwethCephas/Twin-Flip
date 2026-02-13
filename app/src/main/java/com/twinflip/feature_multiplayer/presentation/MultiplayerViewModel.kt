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

                val playerOne = Player(name = "Player 1", isActive = true)
                val playerTwo = Player(name = "Player 2", isActive = false)

                _multiplayerUiState.update {
                    it.copy(
                        cards = shuffledCards,
                        playerOne = playerOne,
                        playerTwo = playerTwo,
                        activePlayerId = playerOne.id.toString(),
                        turnNumber = 1,
                        isComparing = false,
                        firstSelected = null,
                        secondSelected = null,
                        gamePhase = GamePhase.Idle,
                        winnerId = "",
                        matchedPairs = 0
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
        val currentCard = state.cards.find { it.id == card.id } ?: return

        if (state.isComparing) return
        if (currentCard.isFlipped || currentCard.isMatched) return

        if (state.firstSelected == null) {
            flipCard(currentCard.id)
            _multiplayerUiState.update { it.copy(firstSelected = currentCard) }
            return
        }

        if (state.secondSelected == null) {
            flipCard(currentCard.id)
            _multiplayerUiState.update {
                it.copy(secondSelected = currentCard, isComparing = true)
            }
            compareCards()
        }
    }

    private fun compareCards() {
        viewModelScope.launch {
            delay(800)

            val state = _multiplayerUiState.value

            val first = state.firstSelected!!
            val second = state.secondSelected!!

            if (first.card.name == second.card.name) {

                markAsMatched(first.id)
                markAsMatched(second.id)

                _multiplayerUiState.update { currentState ->
                    val newMatched = currentState.matchedPairs + 1
                    val activePlayerIsOne =
                        currentState.activePlayerId == currentState.playerOne?.id.toString()

                    val updatedPlayerOne = if (activePlayerIsOne) {
                        val newPairs = currentState.playerOne?.matchedPairs?.plus(1) ?: 0
                        currentState.playerOne?.copy(
                            matchedPairs = newPairs,
                            score = ((newPairs.toDouble() / (currentState.cards.size / 2).toDouble()) * 1000).toInt()
                        )
                    } else {
                        currentState.playerOne
                    }

                    val updatedPlayerTwo = if (!activePlayerIsOne) {
                        val newPairs = currentState.playerTwo?.matchedPairs?.plus(1) ?: 0
                        currentState.playerTwo?.copy(
                            matchedPairs = newPairs,
                            score = ((newPairs.toDouble() / (currentState.cards.size / 2).toDouble()) * 1000).toInt()
                        )
                    } else {
                        currentState.playerTwo
                    }

                    val isGameFinished = newMatched == (currentState.cards.size / 2)
                    val winnerId = if (isGameFinished) {
                        when {
                            updatedPlayerOne!!.score > updatedPlayerTwo!!.score -> updatedPlayerOne.name
                            updatedPlayerTwo.score > updatedPlayerOne.score -> updatedPlayerTwo.name
                            else -> "draw"
                        }
                    } else {
                        currentState.winnerId
                    }

                    currentState.copy(
                        matchedPairs = newMatched,
                        playerOne = updatedPlayerOne,
                        playerTwo = updatedPlayerTwo,
                        gamePhase = if (isGameFinished) GamePhase.Finished else GamePhase.InProgress,
                        winnerId = winnerId,
                        firstSelected = null,
                        secondSelected = null,
                        isComparing = false
                    )
                }

            } else {
                flipCardBack(first.id)
                flipCardBack(second.id)
                switchTurns()

                _multiplayerUiState.update {
                    it.copy(
                        firstSelected = null,
                        secondSelected = null,
                        isComparing = false
                    )
                }
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
