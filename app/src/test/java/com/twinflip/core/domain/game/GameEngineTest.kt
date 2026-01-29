package com.twinflip.core.domain.game

import com.twinflip.core.domain.model.CardData
import com.twinflip.core.domain.usecase.CardsUseCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GameEngineTest {
    private lateinit var cardsUseCase: CardsUseCase
    private lateinit var gameEngine: GameEngine

    private val themeName = "animals"


    @Before
    fun setUp() {
        cardsUseCase = mock()
        gameEngine = GameEngine(cardsUseCase)
    }

    @Test
    fun `loadGames should return a doubled and shuffled list of cards`() {

        val mockCards = listOf(
            CardData(name = "card1", imagePath = "path1"),
            CardData(name = "card2", imagePath = "path2")
        )

        whenever(cardsUseCase.invoke(themeName)).thenReturn(mockCards)


        val result = gameEngine.loadGames(themeName)

        assertNotNull(result)
        assertEquals(4, result.size)
        assertEquals(2, result.count { it.card.name == "card1" })
        assertEquals(2, result.count { it.card.name == "card2" })

    }

    @Test
    fun `flipcard should flip a card when it's called`() {

        val cards = listOf(
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

        val result = gameEngine.flipCard(cards, 1)

        assertNotNull(result)
        assertTrue(result.first { it.id == 1 }.isFlipped)
        assertFalse(result.first { it.id == 2 }.isFlipped)

    }

    @Test
    fun `flipCardBack should flip a card back when it's called`() {
        val cards = listOf(
            GameCard(
                id = 1,
                card = CardData(
                    name = "card1",
                    imagePath = "path1"
                ),
                isFlipped = true,
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

        val result = gameEngine.flipCardBack(cards, 1)

        assertNotNull(result)
        assertFalse(result.first { it.id == 1 }.isFlipped)
    }

    @Test
    fun `markAsMatched should mark a card as matched when cards match`() {
        val cards = listOf(
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
        val result = gameEngine.markAsMatched(cards, 1)

        assertNotNull(result)
        assertTrue(result.first { it.id == 1 }.isMatched)
        assertFalse(result.first { it.id == 2 }.isMatched)


    }
}