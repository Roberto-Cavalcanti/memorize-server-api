package dev.roberto.memorize;

import dev.roberto.memorize.model.Card;
import dev.roberto.memorize.model.Deck;
import dev.roberto.memorize.service.FlashcardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemorizeApplicationTests {

    @Autowired
    private FlashcardService flashcardService;

    @Test
    void contextLoads() {
        assertNotNull(flashcardService);
    }

    @Test
    void testCreateAndRetrieveDeckAndCard() {
        Deck deck = new Deck("Java Basics", "Learn Java fundamentals");
        Deck savedDeck = flashcardService.createDeck(deck);
        assertNotNull(savedDeck.getId());

        Card card = new Card(savedDeck.getId(), "What is Java?", "A programming language");
        Card savedCard = flashcardService.createCard(savedDeck.getId(), card);
        assertNotNull(savedCard.getId());
        assertEquals(savedDeck.getId(), savedCard.getDeckId());

        var cards = flashcardService.getCardsByDeckId(savedDeck.getId());
        assertEquals(1, cards.size());

        flashcardService.deleteDeck(savedDeck.getId());
        assertTrue(flashcardService.getAllDecks().isEmpty());
    }
}
