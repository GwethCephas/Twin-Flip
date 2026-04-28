package com.twinflip.feature_singleplayer.presentation.game

import com.twinflip.common.MainDispatcherRule
import com.twinflip.core.domain.game.GameCard
import com.twinflip.core.domain.game.GameEngine
import com.twinflip.core.domain.model.CardData
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var gameEngine: GameEngine
    private lateinit var gameViewModel: GameViewModel

    private val themeName = "animals"
    private val dummyCards = listOf(
        GameCard(
            id = 1,
            card = CardData(name = "card1", imagePath = "path1"),
            isFlipped = false,
            isMatched = false
        ),
        GameCard(
            id = 2,
            card = CardData(name = "card2", imagePath = "path2"),
            isFlipped = false,
            isMatched = false
        )
    )

    @Before
    fun setUp() {
        gameEngine = mockk()
        every { gameEngine.loadGames(any()) } returns dummyCards
    }

    @After
    fun tearDown() {
        if (::gameViewModel.isInitialized) {
            gameViewModel.stopTimer()
        }
        clearAllMocks()
    }

    @Test
    fun `loadGames update the ui state correctly`() = runTest {
        gameViewModel = GameViewModel(gameEngine)
        gameViewModel.loadGames(themeName)
        advanceTimeBy(100)

        val state = gameViewModel.gameUiState.value
        assertEquals(dummyCards, state.cards)
        assertEquals(0, state.moves)
        assertEquals(0, state.matchedPairs)
        assertFalse(state.gameCompleted)
    }

    @Test
    fun `cardClicked selects first card`() = runTest {
        every { gameEngine.flipCard(any(), any()) } returns dummyCards

        gameViewModel = GameViewModel(gameEngine)
        gameViewModel.loadGames(themeName)
        advanceTimeBy(100)

        gameViewModel.cardClicked(dummyCards[0])
        advanceTimeBy(100)

        val state = gameViewModel.gameUiState.value
        assertEquals(dummyCards[0].id, state.firstSelected?.id)
        assertNull(state.secondSelected)
    }

    @Test
    fun `matching cards are marked and increase matchedPairs`() = runTest {
        val matchingCards = listOf(
            GameCard(id = 1, card = CardData("match", "path")),
            GameCard(id = 2, card = CardData("match", "path"))
        )
        every { gameEngine.loadGames(themeName) } returns matchingCards
        every { gameEngine.flipCard(any(), any()) } returns matchingCards
        every { gameEngine.markAsMatched(any(), any()) } returns matchingCards

        gameViewModel = GameViewModel(gameEngine)
        gameViewModel.loadGames(themeName)
        advanceTimeBy(100)

        gameViewModel.cardClicked(matchingCards[0])
        gameViewModel.cardClicked(matchingCards[1])

        advanceTimeBy(1000)

        val state = gameViewModel.gameUiState.value
        assertEquals(1, state.matchedPairs)
        assertFalse(state.isComparing)
        gameViewModel.stopTimer()
    }

    @Test
    fun `non matching cards are flipped back`() = runTest {
        every { gameEngine.flipCard(any(), any()) } returns dummyCards
        every { gameEngine.flipCardBack(any(), any()) } returns dummyCards

        gameViewModel = GameViewModel(gameEngine)
        gameViewModel.loadGames(themeName)
        advanceTimeBy(100)

        gameViewModel.cardClicked(dummyCards[0])
        gameViewModel.cardClicked(dummyCards[1])

        advanceTimeBy(1000)

        val state = gameViewModel.gameUiState.value
        assertEquals(0, state.matchedPairs)
        assertNull(state.firstSelected)
        assertNull(state.secondSelected)
        gameViewModel.stopTimer()
    }

    @Test
    fun `game completion sets gameCompleted true`() = runTest {
        val matchingCards = listOf(
            GameCard(id = 1, card = CardData("match", "path")),
            GameCard(id = 2, card = CardData("match", "path"))
        )
        every { gameEngine.loadGames(themeName) } returns matchingCards
        every { gameEngine.flipCard(any(), any()) } returns matchingCards
        every { gameEngine.markAsMatched(any(), any()) } returns matchingCards

        gameViewModel = GameViewModel(gameEngine)
        gameViewModel.loadGames(themeName)
        advanceTimeBy(100)

        gameViewModel.cardClicked(matchingCards[0])
        gameViewModel.cardClicked(matchingCards[1])

        advanceTimeBy(1000)

        val state = gameViewModel.gameUiState.value
        assertTrue(state.gameCompleted)
        gameViewModel.stopTimer()
    }
}
