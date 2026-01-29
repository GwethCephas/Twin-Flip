package com.twinflip.feature_singleplayer.presentation.game

import app.cash.turbine.test
import com.twinflip.core.domain.game.GameCard
import com.twinflip.core.domain.game.GameEngine
import com.twinflip.core.domain.model.CardData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelTest {

    lateinit var gameEngine: GameEngine
    lateinit var gameViewModel: GameViewModel

    private val themeName = "animals"
    private val dummyCards = listOf(
        GameCard(
            id = 1,
            card = CardData(
                name = "card1",
                imagePath = "path1"
            ),
            isFlipped = false,
            isMatched = false
        ),
        GameCard(
            id = 2,
            card = CardData(
                name = "card2",
                imagePath = "path2"
            ),
            isFlipped = false,
            isMatched = false
        )
    )
    private val matchingCards = listOf(
        dummyCards[0],
        dummyCards[0].copy(id = 2)
    )

    @Before
    fun setUp() {
        gameEngine = mock()
    }

    @Test
    fun `loadGames update the ui state correctly`() = runTest {

        whenever(gameEngine.loadGames(themeName)).thenReturn(dummyCards)

        gameViewModel = GameViewModel(gameEngine)

        gameViewModel.loadGames(themeName)
        advanceUntilIdle()

        val state = gameViewModel.gameUiState

        state.test {
            val initialState = awaitItem()

            assertEquals(dummyCards, initialState.cards)
            assertEquals(0, initialState.moves)
            assertEquals(0, initialState.matchedPairs)
            assertFalse(initialState.gameCompleted)
            assertFalse(initialState.isComparing)
            assertNull(initialState.firstSelected)
            assertNull(initialState.secondSelected)
            assertEquals("00:00", initialState.time)
            cancelAndIgnoreRemainingEvents()
        }


    }


    @Test
    fun `cardClicked selects first card`() = runTest {
        whenever(gameEngine.loadGames(themeName)).thenReturn(dummyCards)
        whenever(gameEngine.flipCard(any(), any())).thenReturn(dummyCards)

        gameViewModel = GameViewModel(gameEngine)
        gameViewModel.loadGames(themeName)
        advanceUntilIdle()

        gameViewModel.cardClicked(dummyCards[0])

        val state = gameViewModel.gameUiState.value
        assertEquals(dummyCards[0], state.firstSelected)
        assertNull(state.secondSelected)
        assertFalse(state.isComparing)
    }


    @Test
    fun `matching cards are marked and increase matchedPairs`() = runTest {

        whenever(gameEngine.loadGames(themeName)).thenReturn(matchingCards)
        whenever(gameEngine.flipCard(any(), any())).thenReturn(matchingCards)
        whenever(gameEngine.markAsMatched(any(), any())).thenReturn(matchingCards)

        gameViewModel = GameViewModel(gameEngine)
        gameViewModel.loadGames(themeName)
        advanceUntilIdle()

        gameViewModel.cardClicked(matchingCards[0])
        gameViewModel.cardClicked(matchingCards[1])

        advanceTimeBy(800) // comparison delay
        advanceUntilIdle()

        gameViewModel.gameUiState.test {
            val state = awaitItem()
            assertNotNull(state.cards)
            assertEquals(0, state.matchedPairs)
            assertEquals(0, state.moves)
            assertTrue(state.isComparing)



            val finalState = awaitItem()
            assertNotNull(finalState.cards)
            assertEquals(1, finalState.matchedPairs)



            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `non matching cards are flipped back`() = runTest {
        whenever(gameEngine.loadGames(themeName)).thenReturn(dummyCards)
        whenever(gameEngine.flipCard(any(), any())).thenReturn(dummyCards)
        whenever(gameEngine.flipCardBack(any(), any())).thenReturn(dummyCards)

        gameViewModel = GameViewModel(gameEngine)
        gameViewModel.loadGames(themeName)
        advanceUntilIdle()

        gameViewModel.cardClicked(dummyCards[0])
        gameViewModel.cardClicked(dummyCards[1])

        advanceTimeBy(800)
        advanceUntilIdle()


        gameViewModel.gameUiState.test {
            val state = awaitItem()
            assertNotNull(state.cards)
            assertEquals(0, state.matchedPairs)
            assertEquals(0, state.moves)
            assertTrue(state.isComparing)

            val finalState = awaitItem()
            assertNotNull(finalState.cards)
            assertEquals(0, state.matchedPairs)


            cancelAndIgnoreRemainingEvents()

        }

    }

    @Test
    fun `game completion sets gameCompleted true`() = runTest {
        whenever(gameEngine.loadGames(themeName)).thenReturn(matchingCards)
        whenever(gameEngine.flipCard(any(), any())).thenReturn(matchingCards)
        whenever(gameEngine.markAsMatched(any(), any())).thenReturn(matchingCards)

        gameViewModel = GameViewModel(gameEngine)
        gameViewModel.loadGames(themeName)
        advanceUntilIdle()

        gameViewModel.cardClicked(matchingCards[0])
        gameViewModel.cardClicked(matchingCards[1])

        advanceUntilIdle()

        gameViewModel.gameUiState.test {
            val firstState = awaitItem()
            assertNotNull(firstState.cards)
            assertEquals(0, firstState.matchedPairs)
            assertFalse(firstState.gameCompleted)

            val secondState = awaitItem()
            assertNotNull(secondState.cards)
            assertEquals(1, secondState.matchedPairs)

            val lastState = awaitItem()
            assertTrue(lastState.gameCompleted)

            cancelAndIgnoreRemainingEvents()

        }
    }

}