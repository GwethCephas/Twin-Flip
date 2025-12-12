package com.twinflip.presentation.game

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twinflip.domain.usecase.CardsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GameViewModel(
    private val cardsUseCase: CardsUseCase
) : ViewModel() {

    private var timerJob: Job? = null

    private var seconds = 0

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
                    secondSelected = null,
                    time = "00:00"
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

            startTimer()

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
                if (state.matchedPairs == (state.cards.size / 2)) {
                    delay(1000)
                    stopTimer()
                    delay(1000)
                    _gameUiState.update {
                        it.copy(
                            time = formatTime(0),
                            score = 0
                        )
                    }
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

    fun startTimer() {
        timerJob?.cancel()

        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                seconds++
                _gameUiState.update {
                    it.copy(
                        time = formatTime(seconds)
                    )
                }
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        seconds = 0

        timerJob = null
    }

    @SuppressLint("DefaultLocale")
    private fun formatTime(totalSeconds: Int): String {
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)

    }

}