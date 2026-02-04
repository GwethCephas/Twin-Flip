package com.twinflip.core.data.repository

import com.twinflip.core.data.datasource.ThemeProvider
import com.twinflip.core.data.model.CardEntity
import com.twinflip.core.data.model.ThemeEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CardRepositoryImplTest {

    lateinit var themeProvider: ThemeProvider
    lateinit var repository: CardRepositoryImpl

    @Before
    fun setUp() {
        themeProvider = mock()
        repository = CardRepositoryImpl(themeProvider)
    }

    @Test
    fun getCardsForTheme_returnsAllThemes() {
        val themes = repository.getCardsForTheme("animals")
        assertNotNull(themes)
    }

    @Test
    fun getRandomCards_returnsMappedCards() {
        val mockCards = listOf(
            CardEntity(name = "card1", imagePath = "path1/card1.svg"),
            CardEntity(name = "card2", imagePath = "path2/card2.svg"),
        )
        val theme = ThemeEntity("animals", mockCards)

        whenever(themeProvider.getThemes()).thenReturn(listOf(theme))

        val randomCards = repository.getRandomCards()

        assertNotNull(randomCards)
        assertEquals(2, randomCards.size)
        assertEquals("card1", randomCards[0].name)
        assertEquals("card2", randomCards[1].name)

        verify(themeProvider).getThemes()

    }

}