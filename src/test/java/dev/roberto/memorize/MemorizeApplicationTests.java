package dev.roberto.memorize;

import dev.roberto.memorize.model.Card;
import dev.roberto.memorize.model.Deck;
import dev.roberto.memorize.model.User;
import dev.roberto.memorize.repository.UserRepository;
import dev.roberto.memorize.security.JwtTokenProvider;
import dev.roberto.memorize.service.FlashcardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemorizeApplicationTests {

    @Autowired
    private FlashcardService flashcardService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private User testUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        testUser = new User("testuser", "test@memorize.dev", passwordEncoder.encode("password123"));
        userRepository.save(testUser);
    }

    @Test
    void contextLoads() {
        assertNotNull(flashcardService);
        assertNotNull(jwtTokenProvider);
    }

    @Test
    void testGenerateHash() {
        String rawPassword = "123456";
        String hash = passwordEncoder.encode(rawPassword);
        System.out.println("========================================");
        System.out.println("BCRYPT HASH: " + hash);
        System.out.println("========================================");
        assertNotNull(hash);
    }

    @Test
    void testJwtTokenGenerationAndValidation() {
        String token = jwtTokenProvider.generateToken(testUser.getUsername(), testUser.getId());
        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
        assertEquals(testUser.getUsername(), jwtTokenProvider.getUsernameFromToken(token));
        assertEquals(testUser.getId(), jwtTokenProvider.getUserIdFromToken(token));
    }

    @Test
    void testPasswordEncodingAndMatching() {
        String rawPassword = "mySecretPassword";
        String encoded = passwordEncoder.encode(rawPassword);
        assertTrue(passwordEncoder.matches(rawPassword, encoded));
        assertFalse(passwordEncoder.matches("wrongPassword", encoded));
    }

    @Test
    void testCreateAndRetrieveDeckAndCardWithAuthentication() {
        Deck deck = new Deck("Java Basics", "Learn Java fundamentals");
        Deck savedDeck = flashcardService.createDeck(deck, testUser.getId());
        assertNotNull(savedDeck.getId());
        assertEquals(testUser.getId(), savedDeck.getUserId());

        Card card = new Card(savedDeck.getId(), "What is Java?", "A programming language");
        Card savedCard = flashcardService.createCard(savedDeck.getId(), card, testUser.getId());
        assertNotNull(savedCard.getId());
        assertEquals(savedDeck.getId(), savedCard.getDeckId());

        List<Card> cards = flashcardService.getCardsByDeckId(savedDeck.getId(), testUser.getId());
        assertEquals(1, cards.size());

        flashcardService.deleteDeck(savedDeck.getId(), testUser.getId());
        assertTrue(flashcardService.getAllDecks(testUser.getId()).isEmpty());
    }

    @Test
    void testDataIsolationBetweenUsers() {
        User otherUser = new User("otheruser", "other@memorize.dev", passwordEncoder.encode("password123"));
        userRepository.save(otherUser);

        Deck deckForUser1 = flashcardService.createDeck(new Deck("User 1 Deck", "Secret"), testUser.getId());

        // Other user should not be able to see or delete User 1's deck
        assertTrue(flashcardService.getAllDecks(otherUser.getId()).isEmpty());
        assertTrue(flashcardService.getDeckById(deckForUser1.getId(), otherUser.getId()).isEmpty());
        assertFalse(flashcardService.deleteDeck(deckForUser1.getId(), otherUser.getId()));

        // User 1 should see it
        assertEquals(1, flashcardService.getAllDecks(testUser.getId()).size());
    }
}
