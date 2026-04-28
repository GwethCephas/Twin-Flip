package com.twinflip.feature_multiplayer.presentation

import app.cash.turbine.test
import com.twinflip.common.MainDispatcherRule
import com.twinflip.core.domain.game.GameCard
import com.twinflip.core.domain.game.GameEngine
import com.twinflip.core.domain.model.CardData
import io.mockk.clearAllMocks
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MultiplayerViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var gameEngine: GameEngine
    private lateinit var multiplayerViewModel: MultiplayerViewModel

    private val themeName = "animals"

    private val dummyCards = listOf(
        GameCard(
            id = 1,
            card = CardData(name = "lion", imagePath = "lion"),
            isFlipped = false,
            isMatched = false
        ),
        GameCard(
            id = 2,
            card = CardData(name = "lion", imagePath = "lion"),
            isFlipped = false,
            isMatched = false
        )
    )

    @Before
    fun setUp() {
        gameEngine = mockk()

        every { gameEngine.loadGames(any()) } returns dummyCards

        every { gameEngine.flipCard(any(), any()) } answers {
            val cards = it.invocation.args[0] as List<GameCard>
            val id = it.invocation.args[1] as Int
            cards.map { if (it.id == id) it.copy(isFlipped = true) else it }
        }

        every { gameEngine.flipCardBack(any(), any()) } answers {
            val cards = it.invocation.args[0] as List<GameCard>
            val id = it.invocation.args[1] as Int
            cards.map { if (it.id == id) it.copy(isFlipped = false) else it }
        }

        every { gameEngine.markAsMatched(any(), any()) } answers {
            val cards = it.invocation.args[0] as List<GameCard>
            val id = it.invocation.args[1] as Int
            cards.map {
                if (it.id == id || it.card.name == "lion") it.copy(isMatched = true) else it
            }
        }


        multiplayerViewModel = MultiplayerViewModel(gameEngine)
    }

    @Test
    fun `loadGame sets initial state correctly`() = runTest {

        multiplayerViewModel.loadGame("animals")

        advanceUntilIdle()

        multiplayerViewModel.multiplayerUiState.test {
            val state = awaitItem()
            assertEquals(GamePhase.Idle, state.gamePhase)

            assertEquals(2, state.cards.size)

            assertNotNull(state.playerOne)
            assertNotNull(state.playerTwo)

            assertTrue(state.playerOne!!.isActive)
            assertFalse(state.playerTwo!!.isActive)

            assertEquals(state.playerOne.id.toString(), state.activePlayerId)

            assertEquals(1, state.turnNumber)

            assertNull(state.firstSelected)
            assertNull(state.secondSelected)
            assertFalse(state.isComparing)
        }


    }

    @Test
    fun `cardClicked selects first card`() = runTest {
        multiplayerViewModel.loadGame(themeName)
        advanceUntilIdle()

        val card = dummyCards[0]
        multiplayerViewModel.cardClicked(card)
        advanceUntilIdle()

        val state = multiplayerViewModel.multiplayerUiState.value
        assertEquals(card, state.firstSelected)
        assertNull(state.secondSelected)
        assertFalse(state.isComparing)
    }


    @Test
    fun `matching cards increase score and do not switch turn`() = runTest {
        multiplayerViewModel.loadGame("animals")
        multiplayerViewModel.startGame()

        advanceUntilIdle()

        val firstCard = dummyCards[0]
        val secondCard = dummyCards[1]

        multiplayerViewModel.cardClicked(firstCard)
        multiplayerViewModel.cardClicked(secondCard)

        advanceTimeBy(800)
        advanceUntilIdle()

        val finalState = multiplayerViewModel.multiplayerUiState.value

        assertEquals(1, finalState.playerOne?.matchedPairs)
    }


    @Test
    fun `non matching cards switch turn`() = runTest {
        val nonMatchingCards = listOf(
            GameCard(1, CardData("lion", "lion")),
            GameCard(2, CardData("tiger", "tiger"))
        )

        every { gameEngine.loadGames(any()) } returns nonMatchingCards

        multiplayerViewModel.loadGame("animals")
        advanceUntilIdle()

        multiplayerViewModel.startGame()

        val firstPlayer = multiplayerViewModel.multiplayerUiState.value.activePlayerId

        multiplayerViewModel.cardClicked(nonMatchingCards[0])
        multiplayerViewModel.cardClicked(nonMatchingCards[1])

        advanceTimeBy(800)
        advanceUntilIdle()

        multiplayerViewModel.multiplayerUiState.test {
            val state = awaitItem()

            assertEquals(0, state.matchedPairs)
            assertNotEquals(firstPlayer, state.activePlayerId)

            cancelAndIgnoreRemainingEvents()
        }


    }

    @After
    fun tearDown() {
        clearMocks(gameEngine)
        clearAllMocks()
    }
}
