package com.twinflip.presentation.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twinflip.domain.usecase.CardsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val cardsUseCase: CardsUseCase
) : ViewModel() {


    private var _gameUiState = MutableStateFlow(GameUiState())
    val gameUiState = _gameUiState.asStateFlow()

    fun loadGames(themeName: String) {
        viewModelScope.launch {
            val cards = cardsUseCase.invoke(themeName)

            val shuffledCards = (cards + cards)
                .shuffled()
                .mapIndexed { index, card ->
                    GameCard(
                        id = index,
                        card = card
                    )
                }

            _gameUiState.update {
                it.copy(
                    cards = shuffledCards,
                    moves = 0,
                    matchedPairs = 0,
                    gameCompleted = false,
                    isComparing = false,
                    firstSelected = null,
                    secondSelected = null
                )
            }
        }
    }

    fun cardClicked(card: GameCard) {
        val state = _gameUiState.value

        if (state.isComparing) return

        if (card.isFlipped || card.isMatched) return

        if (state.firstSelected == null) {
            flipCard(card.id)
            _gameUiState.update {
                it.copy(firstSelected = card)
            }
            return
        }
        if (state.secondSelected == null) {
            flipCard(card.id)
            _gameUiState.update {
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
            val state = _gameUiState.value

            delay(800)

            val first = state.firstSelected!!
            val second = state.secondSelected!!

            if (first.card.name == second.card.name) {
                markAsMatched(first.id)
                markAsMatched(second.id)

                val newMatched = state.matchedPairs + 1

                _gameUiState.update {
                    it.copy(
                        matchedPairs = newMatched,
                        gameCompleted = newMatched == (state.cards.size / 2)
                    )
                }
            } else {
                flipCardBack(first.id)
                flipCardBack(second.id)
            }

            _gameUiState.update {
                it.copy(
                    moves = it.moves + 1,
                    firstSelected = null,
                    secondSelected = null,
                    isComparing = false
                )
            }

        }
    }

    fun flipCard(id: Int) {
        _gameUiState.update { state ->
            state.copy(
                cards = state.cards.map {
                    if (it.id == id) it.copy(isFlipped = true) else it
                }
            )
        }
    }

    fun flipCardBack(id: Int) {
        _gameUiState.update { state ->
            state.copy(
                cards = state.cards.map {
                    if (it.id == id) it.copy(isFlipped = false) else it
                }
            )
        }
    }

    fun markAsMatched(id: Int) {
        _gameUiState.update { state ->
            state.copy(
                cards = state.cards.map {
                    if (it.id == id) it.copy(isMatched = true) else it
                }
            )
        }
    }


}